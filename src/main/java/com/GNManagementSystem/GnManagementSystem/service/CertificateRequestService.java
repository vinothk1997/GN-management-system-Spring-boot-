package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.converter.CertificateRequestConverter;
import com.GNManagementSystem.GnManagementSystem.dto.*;
import com.GNManagementSystem.GnManagementSystem.entity.Certificate;
import com.GNManagementSystem.GnManagementSystem.entity.CertificateRequest;
import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.CertificateRepository;
import com.GNManagementSystem.GnManagementSystem.repository.CertificateRequestRepository;
import com.GNManagementSystem.GnManagementSystem.repository.GramaNildhariRepository;
import com.GNManagementSystem.GnManagementSystem.utill.EmailService;
import com.GNManagementSystem.GnManagementSystem.utill.HTMLToPDFService;
import com.GNManagementSystem.GnManagementSystem.utill.SupabaseStorageService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.IntStream;
import java.util.Random;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CertificateRequestService {
    private final CertificateRequestRepository certificateRequestRepository;
    private final CertificateRequestConverter certificateRequestConverter;
    private final GramaNildhariRepository gramaNildhariRepository;
    private final SupabaseStorageService supabaseStorageService;
    private final HTMLToPDFService htmlToPDFService;
    private final CertificateRepository certificateRepository;
    private final EmailService emailService;

    public ResponseDto save(CertificateRequestDto certificateRequestDto) {
        CertificateRequest certificateRequest = certificateRequestConverter.convertCertificateRequest(certificateRequestDto);
        certificateRequestRepository.save(certificateRequest);
        return new ResponseDto("Certificate Request saved successfully");
    }

   public List<CertificateRequestResponseDto> getAllCertificateRequests() {
        List<CertificateRequest> certificateRequests = certificateRequestRepository.findAll();
        return certificateRequests.stream().map(certificateRequestConverter::convertToCertificateRequestResponseDto).toList();
   }

   public List<CertificateRequestResponseDto> getCertificateRequestsByFilter(String userId, TypeOfCertificate typeOfCertificate, RequestStatus requestStatus, LocalDate requestedDate, LocalDate requestedDateTo) {
        return certificateRequestRepository.getCertificateRequestsByFilter(userId,typeOfCertificate,requestStatus,requestedDate,requestedDateTo);
   }

   public ResponseDto updateCertificateRequestStatus(CertificateStatusUpdateDto certificateStatusUpdateDto){
        CertificateRequest certificateRequest = certificateRequestConverter.convertStatus(certificateStatusUpdateDto);
        certificateRequestRepository.save(certificateRequest);
        return new ResponseDto("Certificate Request updated successfully");
   }


    public ResponseDto approveCertificateRequest(String id, String updatedById) throws MessagingException {
        if (!gramaNildhariRepository.existsById(updatedById)) {
            throw new ServiceException("GS not found", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        CertificateRequest certificateRequest = certificateRequestRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Certificate Request not found", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND));

        certificateRequest.setStatus(RequestStatus.APPROVED);
        certificateRequest.setRequestStatusUpdatedBy(updatedById);
        certificateRequest.setRequestStatusUpdateDate(LocalDate.now());
        certificateRequest.setVerificationNumber(generateVerificationNumber());
        log.info("_____________verficationNumber,{}",generateVerificationNumber());
        CertificateRequest savedCertificateRequest = certificateRequestRepository.save(certificateRequest);

        if(certificateRequest.getTypeOfCertificate()==TypeOfCertificate.CHARACTER_CERTIFICATE){

        CertificateRequestDetailsDto certificateRequestDetailsDto = certificateRequestRepository.getCertificateRequestDetailsById(savedCertificateRequest.getId());

        Map<String, Object> data = new HashMap<>();
        data.put("gsDivision", certificateRequestDetailsDto.getGnDivision());
        data.put("dsDivision", "Poonakary");
        data.put("date", LocalDate.now().toString());
        data.put("fullName", certificateRequestDetailsDto.getFirstName()+" "+certificateRequestDetailsDto.getLastName());
        data.put("nic", certificateRequestDetailsDto.getNic());
        data.put("fatherName", certificateRequestDetailsDto.getLastName());
        data.put("motherName", "Kamalini");
        data.put("address", certificateRequestDetailsDto.getAddress()+" "+certificateRequestDetailsDto.getCity());
        data.put("years", "5");
        data.put("behavior", "Exemplary");
        data.put("purpose", certificateRequestDetailsDto.getPurpose());
        data.put("gnName", certificateRequestDetailsDto.getGnName());
        data.put("serialNo",certificateRequestDetailsDto.getSerialNo());

        MultipartFile multipartFile = htmlToPDFService.generatePDFResponse(data);
        String url=  supabaseStorageService.uploadImage(multipartFile);

        Certificate certificate = Certificate.builder()
                .url(url)
                .isCharacterCertificate(true)
                .isNic(false)
                .isBirthCertificate(false)
                .isSignature(false)
                .citizen(certificateRequest.getCitizen())
                .build();

        certificateRepository.save(certificate);


        String fullName= certificateRequestDetailsDto.getFirstName()+" "+certificateRequestDetailsDto.getLastName();

        emailService.sendUrl(certificateRequestDetailsDto.getEmail(),fullName,url,"Character Certificate Link");
        return new ResponseDto("Certificate Request approved successfully");
        }

        Map<String, Object> data = new HashMap<>();
        if (certificateRequest.getTypeOfCertificate() == TypeOfCertificate.INCOME_CERTIFICATE) {
            List<IncomeCertificateResponseDetailDto> incomeCertificateResponseDetailDtos =
                    certificateRequestRepository.getAllIncomeDetailsByCitizenId(certificateRequest.getId());

            if (!incomeCertificateResponseDetailDtos.isEmpty()) {
                BigDecimal totalIncome = incomeCertificateResponseDetailDtos.stream()
                        .map(IncomeCertificateResponseDetailDto::getIncomeAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                IncomeCertificateResponseDetailDto firstIncome = incomeCertificateResponseDetailDtos.get(0);
                data.put("name", firstIncome.getFirstName() + " " + firstIncome.getLastName());
                data.put("address", firstIncome.getStreetAddress() + " " + firstIncome.getCity());
                data.put("date", LocalDate.now().toString());
                data.put("totalIncome", totalIncome);
                data.put("incomes", incomeCertificateResponseDetailDtos);
                data.put("gnName", incomeCertificateResponseDetailDtos.get(0).getGnFirstName() + " " + incomeCertificateResponseDetailDtos.get(0).getGnLastName());
                data.put("gnDivisionName", incomeCertificateResponseDetailDtos.get(0).getGnDivisionName());
                data.put("serialNo",incomeCertificateResponseDetailDtos.get(0).getSerialNo());
            } else {
                throw new ServiceException("No income details found for the given certificate request ID.",ApplicationConstants.NOT_FOUND,HttpStatus.NOT_FOUND);
            }
            MultipartFile multipartFile = htmlToPDFService.generateIncomeCertificate(data);
            String url = supabaseStorageService.uploadImage(multipartFile);

            Certificate certificate = Certificate.builder()
                    .url(url)
                    .isCharacterCertificate(false)
                    .isIncomeCertificate(true)
                    .isNic(false)
                    .isBirthCertificate(false)
                    .isSignature(false)
                    .citizen(certificateRequest.getCitizen())
                    .build();
            certificateRepository.save(certificate);

            String fullName= incomeCertificateResponseDetailDtos.get(0).getFirstName()+" "+incomeCertificateResponseDetailDtos.get(0).getLastName();

            emailService.sendUrl(incomeCertificateResponseDetailDtos.get(0).getEmail(),fullName,url,"Income Certificate Link");
            return new ResponseDto("Certificate Request approved successfully");

        }


        return new ResponseDto("Wrong Certificate Request");
    }
    public ResponseDto rejectCertificateRequest(RejectCertificateDto rejectCertificateDto) {
        CertificateRequest certificateRequest = certificateRequestRepository.findById(rejectCertificateDto.getCertificateId()).orElseThrow(()->new ServiceException("Certificate Id not found",ApplicationConstants.NOT_FOUND,HttpStatus.NOT_FOUND));
        if(certificateRequest.getStatus()==RequestStatus.PENDING){
            certificateRequest.setStatus(RequestStatus.REJECTED);
            certificateRequest.setRequestStatusUpdateDate(LocalDate.now());
            certificateRequest.setRejectionReason(rejectCertificateDto.getRejectReason());
            certificateRequestRepository.save(certificateRequest);
            return new ResponseDto("Certificate Request rejected successfully");
        }
        throw new ServiceException("Certificate Request not rejected", ApplicationConstants.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static String generateVerificationNumber() {
        Random random = new Random();
        return IntStream.range(0, 3)
                .mapToObj(i -> String.format("%04d", random.nextInt(10000)))
                .collect(Collectors.joining("-"));
    }

    public ResponseDto verifyCertificateRequest(String certificateNo){
        CertificateRequest certificateRequest = certificateRequestRepository.findByVerificationNumber(certificateNo).orElseThrow(()->new ServiceException("Certificate Id not found",ApplicationConstants.NOT_FOUND,HttpStatus.NOT_FOUND));
        if(certificateRequest.getVerificationNumber() !=null){
            return new ResponseDto("Certificate Request verified");
        }
        throw new ServiceException("Certificate Request not verified", ApplicationConstants.NOT_FOUND,HttpStatus.NOT_FOUND);
    }

}

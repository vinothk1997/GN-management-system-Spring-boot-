package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestDetailsDto;
import com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestResponseDto;
import com.GNManagementSystem.GnManagementSystem.dto.IncomeCertificateResponseDetailDto;
import com.GNManagementSystem.GnManagementSystem.entity.CertificateRequest;
import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, String> {
    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestResponseDto(" +
            "cr.id, cr.typeOfCertificate, cr.reason, cr.requestedOrganization, cr.status, " +
            "cr.rejectionReason, cr.requestedDate, cr.requestStatusUpdateDate, c.firstName, c.id) " +
            "FROM CertificateRequest cr " +
            "JOIN cr.citizen c " +
            "WHERE (:userId IS NULL OR c.id = :userId) " +
            "AND (:typeOfCertificate IS NULL OR cr.typeOfCertificate = :typeOfCertificate) " +
            "AND (:requestStatus IS NULL OR cr.status = :requestStatus) " +
            "AND (:requestedDate IS NULL OR cr.requestedDate >= :requestedDate) " +
            "AND (:requestedDateTo IS NULL OR cr.requestedDate <= :requestedDateTo)")
    List<CertificateRequestResponseDto> getCertificateRequestsByFilter(
            @Param("userId") String userId,
            @Param("typeOfCertificate") TypeOfCertificate typeOfCertificate,
            @Param("requestStatus") RequestStatus requestStatus,
            @Param("requestedDate") LocalDate requestedDate,
            @Param("requestedDateTo") LocalDate requestedDateTo
    );

    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.CertificateRequestDetailsDto( " +
            "c.firstName, c.lastName, c.email, c.nic, c.address, c.city, " +
            "cr.reason, gnd.code, gn.firstName) " +
            "FROM CertificateRequest cr " +
            "JOIN cr.citizen c " +
            "JOIN c.gramaNiladhariDivision gnd " +
            "JOIN GramaNiladhari gn ON gn.gramaNiladhariDivision = gnd " +
            "WHERE cr.id = :id")
    CertificateRequestDetailsDto getCertificateRequestDetailsById(@Param("id") String id);

    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.IncomeCertificateResponseDetailDto(" +
            "i.id, c.firstName, c.lastName,c.email, c.address, c.city, i.id, i.incomeType, i.incomeSource, i.incomeValue, " +
            "gn.firstName, gn.lastName, gnd.name, gnd.address) " +
            "FROM CertificateRequest cr " +
            "JOIN cr.citizen c " +
            "JOIN Income i ON i.citizen = c " +
            "JOIN c.gramaNiladhariDivision gnd " +
            "JOIN GramaNiladhari gn ON gn.gramaNiladhariDivision=gnd " +
            "WHERE cr.id = :id")
    List<IncomeCertificateResponseDetailDto> getAllIncomeDetailsByCitizenId(@Param("id") String id);
}

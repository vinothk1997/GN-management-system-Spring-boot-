package com.GNManagementSystem.GnManagementSystem.scheduler;


import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import com.GNManagementSystem.GnManagementSystem.repository.CitizenRepository;
import com.GNManagementSystem.GnManagementSystem.utill.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgeCheckScheduler {

    private final CitizenRepository citizenRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void checkIfPersonWillBe18InOneMonth() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthBefore18 = today.plusMonths(1).minusYears(18);

        List<Citizen> citizens = citizenRepository.findByDateOfBirth(oneMonthBefore18);
        log.info("____________citizem,{}",citizens);

        citizens.forEach(
                citizen -> {
                    try {
                        emailService.sendVotingReminderEmail(citizen.getEmail(),citizen.getFirstName());
                    } catch (MessagingException e) {
                        throw new ServiceException(e.getMessage(), ApplicationConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
                    }

                    log.info("Yu have received age 18");
                }
        );

    }
}

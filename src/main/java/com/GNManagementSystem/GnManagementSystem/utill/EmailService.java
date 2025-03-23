package com.GNManagementSystem.GnManagementSystem.utill;

import com.GNManagementSystem.GnManagementSystem.constants.ApplicationConstants;
import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {
    private  JavaMailSender mailSender;
    private TemplateEngine templateEngine;


    public void sendSimpleEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            mailSender.send(message);
            log.info("Email sent successfully to ,{}" , to);
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }

    public void sendHtmlEmail(String to, String name, String resetUrl) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("resetUrl", resetUrl);

            String htmlContent = templateEngine.process("reset-password-email", context);

            helper.setTo(to);
            helper.setSubject("Password Reset Request");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("HTML Email sent successfully to ,{}" , to);
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }

    public void sendVotingReminderEmail(String to, String name) throws MessagingException {

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            Context context = new Context();
            context.setVariable("name", name);
            String htmlContent = templateEngine.process("voting-eligibility-info", context);

            helper.setTo(to);
            helper.setSubject("Voting Reminder");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Voting Reminder email sent successfully to ,{}" , to);
        }
        catch (MessagingException e) {
            throw new ServiceException("Error while sending email: " + e.getMessage(), ApplicationConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);

        }

    }

    public void sendUrl(String to,String name,String url,String typeOfCertificate) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("url", url);
            String htmlContent = templateEngine.process("certificate-link", context);

            helper.setTo(to);
            helper.setSubject(typeOfCertificate);
            helper.setText(htmlContent, true);
            mailSender.send(message);

            log.info("typeOfCertificate email sent successfully to ,{}", to);
        }
        catch (MessagingException e) {
            throw new ServiceException("Error while sending email: " + e.getMessage(), ApplicationConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

    }
}

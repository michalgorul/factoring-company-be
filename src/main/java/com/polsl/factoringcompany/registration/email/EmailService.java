package com.polsl.factoringcompany.registration.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * The type Email service.
 */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    /**
     * the logger bean
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    /**
     * the java mail sender bean
     */
    private final JavaMailSender mailSender;

    /**
     * Sends email to specified receiver
     * @param to    the person that will be a receiver of mail
     * @param email the email
     */
    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("factoring-company@fc.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }
}

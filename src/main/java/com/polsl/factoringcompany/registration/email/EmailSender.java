package com.polsl.factoringcompany.registration.email;

/**
 * The interface Email sender.
 */
public interface EmailSender {
    /**
     * Sends an email
     *
     * @param to    the person that will be a receiver of mail
     * @param email the email
     */
    void send(String to, String email);
}

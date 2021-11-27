package com.polsl.factoringcompany.registration.email;

public interface EmailSender {
    void send(String to, String email);
}

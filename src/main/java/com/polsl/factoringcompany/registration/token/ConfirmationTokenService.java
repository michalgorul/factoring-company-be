package com.polsl.factoringcompany.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationTokenEntity confirmationTokenEntity) {
        confirmationTokenRepository.save(confirmationTokenEntity);
    }

    public ConfirmationTokenEntity getToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(
                token, Timestamp.valueOf(LocalDateTime.now()));
    }
}

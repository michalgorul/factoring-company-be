package com.polsl.factoringcompany.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Confirmation token service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    /**
     * the confirmation token repository bean
     */
    private final ConfirmationTokenRepository confirmationTokenRepository;

    /**
     * Saves confirmation token.
     *
     * @param confirmationTokenEntity the confirmation token entity
     */
    public void saveConfirmationToken(ConfirmationTokenEntity confirmationTokenEntity) {
        confirmationTokenRepository.save(confirmationTokenEntity);
    }

    /**
     * Gets token entity.
     *
     * @param token the token
     * @return the token
     */
    public ConfirmationTokenEntity getToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));
    }

    /**
     * Sets confirmation of token.
     *
     * @param token the token
     */
    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(
                token, Timestamp.valueOf(LocalDateTime.now()));
    }
}

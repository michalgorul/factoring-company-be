package com.polsl.factoringcompany.registration.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Confirmation token entity. Representation of confirmation token in database
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "confirmation_token", schema = "public")
public class ConfirmationTokenEntity {

    /**
     * the id
     */
    @Id
    @SequenceGenerator(
            name = "confirmation_token_id_seq",
            sequenceName = "confirmation_token_id_seq",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_id_seq"
    )
    private Long id;

    /**
     * the token
     */
    @Column(name = "token", nullable = false, length = 150)
    private String token;

    /**
     * the date of token creation
     */
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    /**
     * the date of expiration of token
     */
    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;

    /**
     * the date of confirmation of token
     */
    @Column(name = "confirmed_at")
    private Timestamp confirmedAt;

    /**
     * the user id that token relates to
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * the user entity that token is related to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity userByUserId;


    /**
     * Instantiates a new Confirmation token entity.
     *
     * @param token      the token
     * @param createdAt  the created at
     * @param expiresAt  the expires at
     * @param userEntity the user entity
     */
    public ConfirmationTokenEntity(String token, LocalDateTime createdAt, LocalDateTime expiresAt, UserEntity userEntity) {
        this.token = token;
        this.createdAt = Timestamp.valueOf(createdAt);
        this.expiresAt = Timestamp.valueOf(expiresAt);
        this.userId = Math.toIntExact(userEntity.getId());
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
}

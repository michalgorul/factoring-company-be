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

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "confirmation_token", schema = "public", catalog = "factoring_company")
public class ConfirmationTokenEntity {

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

    @Column(name = "token", nullable = false, length = 150)
    private String token;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;

    @Column(name = "confirmed_at")
    private Timestamp confirmedAt;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity userByUserId;


    public ConfirmationTokenEntity(String token, LocalDateTime createdAt, LocalDateTime expiresAt, UserEntity userEntity) {
        this.token = token;
        this.createdAt = Timestamp.valueOf(createdAt);
        this.expiresAt = Timestamp.valueOf(expiresAt);
        this.userId = Math.toIntExact(userEntity.getId());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

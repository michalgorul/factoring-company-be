package com.polsl.factoringcompany.files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "catalog", nullable = false, length = 50)
    private String catalog;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    public FileEntity(String name, Long size, String contentType, byte[] data, int userId, String catalog) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
        this.userId = userId;
        this.catalog = catalog;
    }

}

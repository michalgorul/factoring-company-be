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

/**
 * The type File entity. Representation of invoice item in database
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "file", schema ="public")
public class FileEntity {

    /**
     * the id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    /**
     * the name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * the size of file
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * the content type
     */
    @Column(name = "content_type", nullable = false)
    private String contentType;

    /**
     * the file content
     */
    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    /**
     * the user id
     */
    @Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * the catalog name
     */
    @Column(name = "catalog", nullable = false, length = 50)
    private String catalog;

    /**
     * the user entity that file is associated with
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    /**
     * Instantiates a new File entity.
     *
     * @param name        the name
     * @param size        the size
     * @param contentType the content type
     * @param data        the data
     * @param userId      the user id
     * @param catalog     the catalog
     */
    public FileEntity(String name, Long size, String contentType, byte[] data, int userId, String catalog) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
        this.userId = userId;
        this.catalog = catalog;
    }

}

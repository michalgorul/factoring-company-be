package com.polsl.factoringcompany.files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * The type File Response.
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class FileResponse {

    /**
     * the id
     */
    private UUID id;

    /**
     * the name of file
     */
    private String name;

    /**
     * the size of file
     */
    private Long size;

    /**
     * the url of file
     */
    private String url;

    /**
     * the type of file. For example application/pdf
     */
    private String contentType;

    /**
     * the user id
     */
    private Integer userId;

    /**
     * the catalog name
     */
    private String catalog;
}

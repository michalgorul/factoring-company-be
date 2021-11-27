package com.polsl.factoringcompany.files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FileResponse {

    private UUID id;
    private String name;
    private Long size;
    private String url;
    private String contentType;
    private Integer userId;
    private String catalog;
}

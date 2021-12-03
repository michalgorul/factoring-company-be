package com.polsl.factoringcompany.files;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * The type File controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/file")
public class FileController {

    /**
     * the file service bean
     */
    private final FileService fileService;

    /**
     * Handles uploading file from frontend side.
     *
     * @param file    the file
     * @param catalog the catalog
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String catalog) {
        return this.fileService.uploadFile(file, catalog);
    }

    /**
     * Gets all files of currently logged user in JWT token.
     *
     * @return the files
     */
    @GetMapping
    public List<FileResponse> getFiles() {
        return this.fileService.getAllFilesCurrentUser();
    }

    /**
     * Gets file specified by id.
     *
     * @param id the id
     * @return the file
     */
    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        return this.fileService.getFile(id);
    }

    /**
     * Gets used space by currently logged user in JWT token.
     *
     * @return the used space
     */
    @GetMapping("/used")
    public Long getUsedSpace() {
        return this.fileService.getUsedSpace();
    }

    /**
     * Deletes file from database.
     *
     * @param id the id
     */
    @DeleteMapping(path = "/{id}")
    public void deleteFile(@PathVariable UUID id) {
        this.fileService.deleteFile(id);
    }

}

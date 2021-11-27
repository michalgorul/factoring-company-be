package com.polsl.factoringcompany.files;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/file")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String catalog) {
        return this.fileService.uploadFile(file, catalog);
    }

    @GetMapping
    public List<FileResponse> getFiles() {
        return this.fileService.getAllFilesCurrentUser();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        return this.fileService.getFile(id);
    }

    @GetMapping("/used")
    public Long getUsedSpace() {
        return this.fileService.getUsedSpace();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteFile(@PathVariable UUID id) {
        this.fileService.deleteFile(id);
    }

}

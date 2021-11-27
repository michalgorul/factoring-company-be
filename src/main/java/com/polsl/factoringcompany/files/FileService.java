package com.polsl.factoringcompany.files;

import com.polsl.factoringcompany.credit.CreditService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserService userService;
    private final CreditService creditService;

    public void save(MultipartFile file, String catalog) throws IOException {

        Long currentUserId = userService.getCurrentUserId();

        try {
            this.fileRepository.save(new FileEntity(
                    StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes(),
                    Math.toIntExact(currentUserId),
                    catalog));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<String> uploadFile(MultipartFile file, String catalog) {
        try {
            this.save(file, catalog);
            if (catalog.equals("credit")) {
                creditService.updateFromProcessingToInReview(file.getOriginalFilename());
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @Transactional
    public ResponseEntity<byte[]> getFile(UUID id) {
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(id);

        if (fileEntityOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }


    public List<FileResponse> getAllFilesCurrentUser() {
        return fileRepository.findAll()
                .stream()
                .filter(x -> x.getUserId() == userService.getCurrentUserId())
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileEntity fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/")
                .path(fileEntity.getId().toString())
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);
        fileResponse.setCatalog(fileEntity.getCatalog());
        fileResponse.setUserId(fileEntity.getUserId());

        return fileResponse;
    }

    public Long getUsedSpace() {
        List<FileResponse> allFilesCurrentUser = this.getAllFilesCurrentUser();

        return allFilesCurrentUser
                .stream()
                .map(FileResponse::getSize)
                .mapToLong(Long::intValue).sum();
    }

    public void deleteFile(UUID id) throws IdNotFoundInDatabaseException {
        try {
            this.fileRepository.deleteById(id);
        } catch (RuntimeException ignored) {
            throw new IdNotFoundInDatabaseException("Currency", id);
        }
    }
}

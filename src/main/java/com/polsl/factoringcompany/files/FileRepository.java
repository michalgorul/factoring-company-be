package com.polsl.factoringcompany.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface file repository. Used for accessing database.
 * @author Michal Goral
 * @version 1.0
 */
@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {

}

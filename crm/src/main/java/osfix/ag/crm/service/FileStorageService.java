package osfix.ag.crm.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
    String storeFileEmployee(MultipartFile file, Long id);
    void deleteFile(String filename);
    void deleteFileWithUri(String filename);
}

package osfix.ag.crm.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import osfix.ag.crm.domain.UploadFileResponse;
import osfix.ag.crm.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fileWithoutDB")
public class FileControllerWithoutDB {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/fileWithoutDB/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }


    @PostMapping("/uploadFile/employee/{id}")
    public UploadFileResponse employeeUploadFile(@PathVariable(name = "id")  long id, @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFileEmployee(file, id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/fileWithoutDB/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles/employee/{id}")
    public List<UploadFileResponse> employeeUploadMultipleFiles(@PathVariable(name = "id")  long id, @RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> employeeUploadFile(id, file))
                .collect(Collectors.toList());
    }

    @PostMapping("/deleteAll/employee/{id}")
    public void deleteAll(Long id) {
         fileStorageService.deleteAll(id);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/delete/{fileName:.+}")
    public void deleteFile(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
    }

    @GetMapping("/delete/employee/{fileName:.+}")
    public void deleteFileByEmployee(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
    }
}

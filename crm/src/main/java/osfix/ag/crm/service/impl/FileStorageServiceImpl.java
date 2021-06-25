package osfix.ag.crm.service.impl;

import org.springframework.stereotype.Service;
import osfix.ag.crm.config.FileStorageProperties;
import osfix.ag.crm.domain.*;
import osfix.ag.crm.domain.manager.Prices;
import osfix.ag.crm.exceptions.FileStorageException;
import osfix.ag.crm.exceptions.MyFileNotFoundException;
import osfix.ag.crm.repo.*;
import osfix.ag.crm.repo.manager.PriceListRepo;
import osfix.ag.crm.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path fileStorageLocation;
    private EmployeePhotoRepo employeePhotoRepo;
    private EmployeeRepo employeeRepo;
    private PriceListRepo priceListRepo;
    private RequestRepo requestRepo;
    private InvoicingRequestRepo invoicingRequestRepo;
    private ShippingDocumentRepo shippingDocumentRepo;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties, EmployeePhotoRepo employeePhotoRepo,
                                  EmployeeRepo employeeRepo, PriceListRepo priceListRepo, RequestRepo requestRepo,
                                  InvoicingRequestRepo invoicingRequestRepo, ShippingDocumentRepo shippingDocumentRepo) {
        this.employeePhotoRepo = employeePhotoRepo;
        this.employeeRepo = employeeRepo;
        this.priceListRepo = priceListRepo;
        this.requestRepo = requestRepo;
        this.invoicingRequestRepo = invoicingRequestRepo;
        this.shippingDocumentRepo = shippingDocumentRepo;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String storeFileEmployee(MultipartFile file, Long id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            return null;
        }
        EmployeePhoto employeePhoto = new EmployeePhoto();
        employeePhoto.setUrl("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/" + fileName);
        employeePhoto.setEmployee(employeeRepo.findById(id).orElse(null));
        employeePhotoRepo.save(employeePhoto);
        return fileName;
    }

    @Override
    public String storeFileInvocing(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            return null;
        }

        InvoicingRequest invoicingRequest = new InvoicingRequest();
        invoicingRequest.setUrl("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/" + fileName);
        invoicingRequest.setRequest(requestRepo.findById(id).orElse(null));
        invoicingRequestRepo.save(invoicingRequest);
        return fileName;
    }

    @Override
    public String storeFileShipping(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            return null;
        }

        ShippingDocument shippingDocument = new ShippingDocument();
        shippingDocument.setUrl("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/" + fileName);
        shippingDocument.setRequest(requestRepo.findById(id).orElse(null));
        shippingDocumentRepo.save(shippingDocument);
        return fileName;
    }

    public String storeFilePrice(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            return null;
        }
        Prices prices = new Prices();
        prices.setUri("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/" + fileName);
        priceListRepo.save(prices);
        return fileName;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        File file = filePath.toFile();
        file.delete();
    }

    @Override
    public void deleteFileWithUri(String filename) {
        String newFileName = filename.replace("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/", "");
        Path filePath = this.fileStorageLocation.resolve(newFileName).normalize();
        File file = filePath.toFile();
        file.delete();
    }

    public void deleteAll(Long id) {
       Employee employee = employeeRepo.findById(id).orElse(null);
       for(EmployeePhoto employeePhoto : employee.getEmployeePhotos()) {
           deleteFileWithUri(employeePhoto.getUrl());
       }
    }

    public void deleteInvoicing(Long id) {
        Request request = requestRepo.findById(id).orElse(null);
        for (InvoicingRequest invoicingRequest : request.getInvoicingRequests()) {
            deleteFileWithUri(invoicingRequest.getUrl());
        }
    }

    @Override
    public void deleteShipping(Long id) {
        Request request = requestRepo.findById(id).orElse(null);
        for (ShippingDocument shippingDocument : request.getShippingDocuments()) {
            deleteFileWithUri(shippingDocument.getUrl());
        }
    }
}

package osfix.ag.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import osfix.ag.crm.domain.admin.FileModel;
import osfix.ag.crm.exceptions.FileErrors;
import osfix.ag.crm.exceptions.FileSaveException;
import osfix.ag.crm.repo.FileRepo;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepo fileRepo;
    public FileModel saveFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains("...")) {
                throw new FileSaveException(FileErrors.INVALID_FILE + filename);
            }
            FileModel model = new FileModel(filename, file.getContentType(), file.getBytes());
            return fileRepo.save(model);

        } catch (Exception e) {
            throw new FileSaveException(FileErrors.FILE_NOT_STORED, e);
        }
    }

    public FileModel getFile(Long fileId) {
        return fileRepo.findById(fileId).orElse(null);
    }
    public List<FileModel> getListOfFiles(){
        return fileRepo.findAll();
    }
}
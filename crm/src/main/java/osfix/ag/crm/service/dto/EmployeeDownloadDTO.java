package osfix.ag.crm.service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeDownloadDTO {
    Long id;
    String name;
    String lastName;
    String middleName;
    String yearOfBirth;
    String citizenship;
    String workshop;
    String position;
    String patent;
    String comment;
    String relevance;
    Date dateOfBirth;
    Date patentExpirationDate;
    Date registrationExpirationDate;
    MultipartFile[] files;
}

package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.EmployeePhoto;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeDTO {

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
    List<EmployeePhotoDTO> employeePhotos;
}

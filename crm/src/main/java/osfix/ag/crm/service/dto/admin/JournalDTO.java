package osfix.ag.crm.service.dto.admin;

import lombok.Data;
import osfix.ag.crm.service.dto.EmployeeDTO;

import java.util.Date;

@Data
public class JournalDTO {
    Long id;
    Long employeeId;
    EmployeeDTO employeeDTO;
    String comment;
    Date date;
}

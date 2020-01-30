package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.Employee;

import java.util.List;

@Data
public class WorkReportDTO {
    Employee employee;
    List<DayDTO> days;

}

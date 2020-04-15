package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class WorkControlDTO {
    Long id;
    Long workListId;
    Integer year;
    Integer month;
    Integer day;
    Double hours;
    Long employeeId;
}

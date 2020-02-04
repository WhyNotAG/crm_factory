package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.domain.product.WorkControlProduct;

import java.util.List;

@Data
public class ReWorkControlDTO {
    Long id;
    WorkList workList;
    Integer year;
    Integer month;
    Integer day;
    Long hours;
    private EmployeeDTO employee;
    List<WorkControlProduct> workControlProduct;
}

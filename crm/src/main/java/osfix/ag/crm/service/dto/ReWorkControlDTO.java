package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;
import osfix.ag.crm.domain.product.WorkControlProduct;

import java.util.List;

@Data
public class ReWorkControlDTO {
    Long id;
    WorkList workList;
    Integer year;
    Integer month;
    Integer day;
    Double hours;
    private EmployeeDTO employee;
    List<WorkControlProductWithoutPhotoDTO> workControlProduct;
    List<PartsWork> partsWorks;
}

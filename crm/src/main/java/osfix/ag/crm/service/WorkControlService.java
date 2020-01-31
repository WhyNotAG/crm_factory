package osfix.ag.crm.service;

import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.service.dto.WorkReportDTO;

import java.util.List;

public interface WorkControlService {
    List<WorkControl> findAll();
    List<WorkControl> findAllByEmployeeId(Long id);
    WorkControl save(WorkControl workControl);
    WorkControl update(Long id, WorkControl workControl);
    void delete(Long id);
    WorkControl findById(Long id);
    WorkControl addProduct(Long id, Long product_id, Long quantity);
    WorkControl deleteProduct(Long id, Long product_id);
    List<WorkControl> findAllByMonth(Integer month);
    WorkReportDTO reportByEmployeeId(Long id, Integer month);
    List<WorkControl> reportByMonth(String month);

}

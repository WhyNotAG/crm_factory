package osfix.ag.crm.service;

import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.WorkControlProduct;

import java.util.List;

public interface WorkControlProductService {
    List<WorkControlProduct> findAll();
    WorkControlProduct findById(Long id);
    WorkControlProduct findByWorkAndProduct(WorkControl workControl, Long productId);
    WorkControlProduct save(WorkControlProduct workControlProduct);
    void delete(Long id);
    WorkControlProduct update(Long id, WorkControlProduct workControlProduct);

}

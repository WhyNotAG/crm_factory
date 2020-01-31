package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.repo.product.WorkControlProductRepo;
import osfix.ag.crm.service.WorkControlProductService;

import java.util.List;

@Service
public class WorkControlProductServiceImpl implements WorkControlProductService {
    private WorkControlProductRepo workControlProductRepo;

    public WorkControlProductServiceImpl(WorkControlProductRepo workControlProductRepo) {
        this.workControlProductRepo = workControlProductRepo;
    }

    @Override
    public List<WorkControlProduct> findAll() {
        return workControlProductRepo.findAll();
    }

    @Override
    public WorkControlProduct findById(Long id) {
        return workControlProductRepo.findById(id).orElse(null);
    }

    @Override
    public WorkControlProduct findByWorkAndProduct(WorkControl workControl, Long productId) {
        return workControlProductRepo.findByWorkControlAndProductId(workControl, productId);
    }

    @Override
    public WorkControlProduct save(WorkControlProduct workControlProduct) {
        return workControlProductRepo.save(workControlProduct);
    }

    @Override
    public void delete(Long id) {
        workControlProductRepo.deleteById(id);
    }

    @Override
    public WorkControlProduct update(Long id, WorkControlProduct workControlProduct) {
        WorkControlProduct workControlProductFromDB = workControlProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(workControlProduct, workControlProductFromDB, "id");
        return workControlProductRepo.save(workControlProductFromDB);
    }
}

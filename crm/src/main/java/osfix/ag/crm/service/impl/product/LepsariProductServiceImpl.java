package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.repo.product.LepsariProductRepo;
import osfix.ag.crm.service.LepsariProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.mapper.LepsariProductMapper;

import java.util.List;

@Service
public class LepsariProductServiceImpl implements LepsariProductService {
    private LepsariProductRepo lepsariProductRepo;

    public LepsariProductServiceImpl(LepsariProductRepo lepsariProductRepo) {
        this.lepsariProductRepo = lepsariProductRepo;
    }

    @Override
    public List<LepsariProduct> findAll() {
        return lepsariProductRepo.findAll();
    }

    @Override
    public LepsariProduct findById(Long id) {
        return lepsariProductRepo.findById(id).orElse(null);
    }

    @Override
    public LepsariProduct update(Long id, RequestProductDTO requestProduct) {
        LepsariProduct lepsariProductFromDb = lepsariProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(requestProduct, lepsariProductFromDb, "id");
        return lepsariProductRepo.save(lepsariProductFromDb);
    }

    @Override
    public LepsariProduct save(LepsariProduct requestProduct) {
        LepsariProduct lemzProduct = requestProduct;
        return lepsariProductRepo.save(lemzProduct);
    }

    @Override
    public LepsariProduct changeStatus(Long id, String status) {
        LepsariProduct lepsariProduct = lepsariProductRepo.findById(id).orElse(null);
        lepsariProduct.setStatus(status);
        return lepsariProductRepo.save(lepsariProduct);
    }

    @Override
    public void delete(Long id) {
        lepsariProductRepo.deleteById(id);
    }
}

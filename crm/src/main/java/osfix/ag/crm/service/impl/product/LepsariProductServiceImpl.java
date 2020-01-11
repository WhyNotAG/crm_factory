package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.repo.product.LepsariProductRepo;
import osfix.ag.crm.service.LepsariProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.mapper.LepsariProductMapper;

import java.util.List;

@Service
public class LepsariProductServiceImpl implements LepsariProductService {
    private LepsariProductRepo lepsariProductRepo;
    private LepsariProductMapper lepsariProductMapper;

    public LepsariProductServiceImpl(LepsariProductRepo lepsariProductRepo, LepsariProductMapper lepsariProductMapper) {
        this.lepsariProductRepo = lepsariProductRepo;
        this.lepsariProductMapper = lepsariProductMapper;
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
    public LepsariProduct save(RequestProductDTO requestProduct) {
        LepsariProduct lemzProduct = lepsariProductMapper.toEntity(requestProduct);
        return lepsariProductRepo.save(lemzProduct);
    }

    @Override
    public void delete(Long id) {
        lepsariProductRepo.deleteById(id);
    }
}

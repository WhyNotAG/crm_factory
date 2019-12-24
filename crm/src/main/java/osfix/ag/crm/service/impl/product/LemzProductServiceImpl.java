package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.repo.product.LemzProductRepo;
import osfix.ag.crm.service.LemzProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.mapper.LemzProductMapper;

import java.util.List;

@Service
public class LemzProductServiceImpl implements LemzProductService {
    private LemzProductRepo lemzProductRepo;
    private LemzProductMapper lemzProductMapper;

    public LemzProductServiceImpl(LemzProductRepo lemzProductRepo, LemzProductMapper lemzProductMapper) {
        this.lemzProductRepo = lemzProductRepo;
        this.lemzProductMapper = lemzProductMapper;
    }

    @Override
    public List<LemzProduct> findAll() {
        return lemzProductRepo.findAll();
    }

    @Override
    public LemzProduct findById(Long id) {
        return lemzProductRepo.findById(id).orElse(null);
    }

    @Override
    public LemzProduct update(Long id, RequestProductDTO requestProduct) {
        LemzProduct lemzProductFromDb = lemzProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(requestProduct, lemzProductFromDb, "id");
        return lemzProductRepo.save(lemzProductFromDb);
    }

    @Override
    public LemzProduct save(RequestProductDTO requestProduct) {
        LemzProduct lemzProduct = lemzProductMapper.toEntity(requestProduct);
        return lemzProductRepo.save(lemzProduct);
    }

    @Override
    public void delete(Long id) {
        lemzProductRepo.deleteById(id);
    }
}

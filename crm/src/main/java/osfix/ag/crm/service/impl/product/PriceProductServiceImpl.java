package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.repo.product.PriceProductRepo;
import osfix.ag.crm.service.PriceProductService;

import java.beans.beancontext.BeanContext;
import java.util.List;

@Service
public class PriceProductServiceImpl implements PriceProductService {
    PriceProductRepo priceProductRepo;

    public PriceProductServiceImpl(PriceProductRepo priceProductRepo) {
        this.priceProductRepo = priceProductRepo;
    }

    @Override
    public List<PriceProduct> findAll() {
        return priceProductRepo.findAll();
    }

    @Override
    public PriceProduct findById(Long id) {
        return priceProductRepo.findById(id).orElse(null);
    }

    @Override
    public PriceProduct save(PriceProduct priceProduct) {
        return priceProductRepo.save(priceProduct);
    }

    @Override
    public PriceProduct update(Long id, PriceProduct priceProduct) {
        PriceProduct priceProductFromDb = priceProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(priceProduct, priceProductFromDb, "id");
        return priceProductRepo.save(priceProductFromDb);
    }

    @Override
    public void delete(Long id) {
        priceProductRepo.deleteById(id);
    }
}

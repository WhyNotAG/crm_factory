package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.PriceGroupProduct;
import osfix.ag.crm.repo.product.PriceGroupProductRepo;
import osfix.ag.crm.service.PriceGroupProductService;

import java.util.List;

@Service
public class PriceGroupProductServiceImpl implements PriceGroupProductService {
    PriceGroupProductRepo priceGroupProductRepo;

    public PriceGroupProductServiceImpl(PriceGroupProductRepo priceGroupProductRepo) {
        this.priceGroupProductRepo = priceGroupProductRepo;
    }

    @Override
    public List<PriceGroupProduct> findAll() {
        return priceGroupProductRepo.findAll();
    }

    @Override
    public PriceGroupProduct findById(Long id) {
        return priceGroupProductRepo.findById(id).orElse(null);
    }

    @Override
    public PriceGroupProduct save(PriceGroupProduct priceGroupProduct) {
        return priceGroupProductRepo.save(priceGroupProduct);
    }

    @Override
    public PriceGroupProduct update(Long id, PriceGroupProduct priceGroupProduct) {
        PriceGroupProduct priceGroupProductFromDb = priceGroupProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(priceGroupProduct, priceGroupProductFromDb, "id");
        return priceGroupProductRepo.save(priceGroupProductFromDb);
    }

    @Override
    public void delete(Long id) {
        priceGroupProductRepo.deleteById(id);
    }
}
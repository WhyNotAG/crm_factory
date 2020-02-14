package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.repo.product.PriceProductRepo;
import osfix.ag.crm.service.PriceProductService;
import osfix.ag.crm.service.dto.PriceDTO;

import java.util.List;

@Service
public class PriceProductServiceImpl implements PriceProductService {
    PriceProductRepo priceProductRepo;

    private double retail;
    private double less1500;
    private double less5000;
    private double partner;
    private double dialler;
    private double distributor;
    private double stopPrice;
    private double stopPriceAll;
    private double oldPrice;

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

    public void setPrice(PriceDTO price) {
        dialler = price.getDialler();
        distributor = price.getDistributor();
        retail = price.getRetail();
        less1500 = price.getLess1500();
        less5000 = price.getLess5000();
        oldPrice = price.getOldPrice();
        partner = price.getPartner();
        stopPrice = price.getStopPrice();
        stopPriceAll = price.getStopPriceAll();
    }

    public PriceDTO getPrice() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setLess1500(less1500);
        priceDTO.setDistributor(distributor);
        priceDTO.setDialler(dialler);
        priceDTO.setLess5000(less5000);
        priceDTO.setOldPrice(oldPrice);
        priceDTO.setPartner(partner);
        priceDTO.setRetail(retail);
        priceDTO.setStopPrice(stopPrice);
        priceDTO.setStopPriceAll(stopPriceAll);
        return priceDTO;
    }
}

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

    private double retailMarketPrice;
    private double units;
    private double cost;
    private double partnerPrice;
    private double dealerPrice;
    private double distributorPrice;
    private double retailPrice;
    private double lessThan1500Price;
    private double lessThan5000Price;

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
        dealerPrice = price.getDealerPrice();
        distributorPrice = price.getDistributorPrice();
        retailMarketPrice = price.getRetailMarketPrice();
        lessThan1500Price = price.getLessThan1500Price();
        lessThan5000Price = price.getLessThan5000Price();
        retailPrice = price.getRetailPrice();
        partnerPrice = price.getPartnerPrice();
        cost = price.getCost();
        units = price.getUnits();
    }

    public PriceDTO getPrice() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setLessThan1500Price(lessThan1500Price);
        priceDTO.setDistributorPrice(distributorPrice);
        priceDTO.setDealerPrice(dealerPrice);
        priceDTO.setLessThan5000Price(lessThan5000Price);
        priceDTO.setCost(cost);
        priceDTO.setPartnerPrice(partnerPrice);
        priceDTO.setRetailMarketPrice(retailMarketPrice);
        priceDTO.setRetailPrice(retailPrice);
        priceDTO.setUnits(units);
        return priceDTO;
    }
}

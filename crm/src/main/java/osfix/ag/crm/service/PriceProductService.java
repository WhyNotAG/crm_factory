package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.service.dto.PriceDTO;

import java.util.List;

public interface PriceProductService {
    List<PriceProduct> findAll();
    PriceProduct findById(Long id);
    PriceProduct save(PriceProduct priceProduct);
    PriceProduct update(Long id, PriceProduct priceProduct);
    void delete(Long id);
    PriceDTO getPrice();
    void setPrice(PriceDTO price);
}

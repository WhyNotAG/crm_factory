package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.PriceGroupProduct;

import java.util.List;

public interface PriceGroupProductService {
    List<PriceGroupProduct> findAll();
    PriceGroupProduct findById(Long id);
    PriceGroupProduct save(PriceGroupProduct priceGroupProduct);
    PriceGroupProduct update(Long id, PriceGroupProduct priceGroupProduct);
    void delete(Long id);
}

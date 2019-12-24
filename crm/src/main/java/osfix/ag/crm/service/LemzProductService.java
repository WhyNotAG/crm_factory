package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

public interface LemzProductService {
    List<LemzProduct> findAll();
    LemzProduct findById(Long id);
    LemzProduct update(Long id, RequestProductDTO requestProduct);
    LemzProduct save(RequestProductDTO requestProduct);
    void delete(Long id);
}

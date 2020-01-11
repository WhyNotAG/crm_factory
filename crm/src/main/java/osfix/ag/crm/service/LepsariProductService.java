package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

public interface LepsariProductService {
    List<LepsariProduct> findAll();
    LepsariProduct findById(Long id);
    LepsariProduct update(Long id, RequestProductDTO requestProduct);
    LepsariProduct save(RequestProductDTO requestProduct);
    void delete(Long id);
}

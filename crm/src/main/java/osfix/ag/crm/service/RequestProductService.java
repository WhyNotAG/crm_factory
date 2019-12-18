package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

public interface RequestProductService {
    List<RequestProduct> findAll();
    RequestProduct findById(Long id);
    RequestProduct update(Long id, RequestProductDTO requestProduct);
    RequestProduct save(RequestProductDTO requestProduct);
    void delete(Long id);
}

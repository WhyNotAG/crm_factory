package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.dto.RequestProductViewDTO;

import java.util.List;

public interface RequestProductService {
    List<RequestProductViewDTO> findAll();
    RequestProductViewDTO findById(Long id);
    RequestProductViewDTO update(Long id, RequestProductDTO requestProduct);
    RequestProductViewDTO save(RequestProductDTO requestProduct);
    RequestProductViewDTO changeStatus(Long id, String status);
    void delete(Long id);
}

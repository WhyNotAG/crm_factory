package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.product.RequestProductRepo;
import osfix.ag.crm.service.RequestProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.mapper.RequestProductMapper;

import java.util.List;

@Service
public class RequestProductServiceImpl implements RequestProductService {
    private RequestProductRepo requestProductRepo;
    private RequestProductMapper requestProductMapper;

    public RequestProductServiceImpl(RequestProductRepo requestProductRepo, RequestProductMapper requestProductMapper) {
        this.requestProductRepo = requestProductRepo;
        this.requestProductMapper = requestProductMapper;
    }

    @Override
    public List<RequestProduct> findAll() {
        return requestProductRepo.findAll();
    }

    @Override
    public RequestProduct findById(Long id) {
        return requestProductRepo.findById(id).orElse(null);
    }

    @Override
    public RequestProduct update(Long id, RequestProductDTO requestProduct) {
        RequestProduct requestProductFromDb = requestProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(requestProduct,requestProductFromDb,"id");
        return requestProductRepo.save(requestProductFromDb);
    }

    @Override
    public RequestProduct save(RequestProductDTO requestProduct) {
        RequestProduct result = requestProductMapper.toEntity(requestProduct);
        return requestProductRepo.save(result);
    }

    @Override
    public void delete(Long id) {
        requestProductRepo.deleteById(id);
    }
}

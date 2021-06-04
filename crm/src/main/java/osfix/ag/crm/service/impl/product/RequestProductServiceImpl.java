package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.repo.factory.GoodsRepo;
import osfix.ag.crm.repo.product.RequestProductRepo;
import osfix.ag.crm.service.GoodsService;
import osfix.ag.crm.service.RequestProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.dto.RequestProductViewDTO;
import osfix.ag.crm.service.mapper.RequestProductMapper;
import osfix.ag.crm.service.mapper.RequestProductViewMapper;

import java.util.List;

@Service
public class RequestProductServiceImpl implements RequestProductService {
    private RequestProductRepo requestProductRepo;
    private RequestProductMapper requestProductMapper;
    private RequestProductViewMapper requestProductViewMapper;
    private RequestRepo requestRepo;
    private GoodsRepo goodsService;

    public RequestProductServiceImpl(RequestProductRepo requestProductRepo, RequestProductMapper requestProductMapper,
                                     GoodsRepo goodsService, RequestProductViewMapper requestProductViewMapper,
                                     RequestRepo requestRepo) {
        this.requestProductRepo = requestProductRepo;
        this.requestProductMapper = requestProductMapper;
        this.goodsService = goodsService;
        this.requestProductViewMapper = requestProductViewMapper;
        this.requestRepo = requestRepo;
    }

    @Override
    public List<RequestProductViewDTO> findAll() {
        return requestProductViewMapper.toDtoList(requestProductRepo.findAll());
    }

    @Override
    public RequestProductViewDTO findById(Long id) {
        return requestProductViewMapper.fromEntity(requestProductRepo.findById(id).orElse(null));
    }

    @Override
    public RequestProductViewDTO update(Long id, RequestProductDTO requestProduct) {
        RequestProduct requestProductFromDb = requestProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(requestProduct,requestProductFromDb,"id");
        requestProductFromDb.setGoods(goodsService.findById(requestProduct.getGoodsId()).orElse(null));
        requestProductFromDb.setRequest(requestRepo.findById(requestProduct.getRequestId()).orElse(null));
        return requestProductViewMapper.fromEntity(requestProductRepo.save(requestProductFromDb));
    }

    @Override
    public RequestProductViewDTO save(RequestProductDTO requestProduct) {
        RequestProduct result = requestProductMapper.toEntity(requestProduct);
        result.setGoods(goodsService.findById(requestProduct.getGoodsId()).orElse(null));
        result.setRequest(requestRepo.findById(requestProduct.getRequestId()).orElse(null));
        return requestProductViewMapper.fromEntity(requestProductRepo.save(result));
    }

    @Override
    public RequestProductViewDTO changeStatus(Long id, String status) {
        RequestProduct requestProduct = requestProductRepo.findById(id).orElse(null);
        requestProduct.setStatus(status);
        return requestProductViewMapper.fromEntity(requestProductRepo.save(requestProduct));
    }

    @Override
    public void delete(Long id) {
        requestProductRepo.deleteById(id);
    }
}

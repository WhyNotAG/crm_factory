package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.factory.GoodsRepo;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.dto.RequestProductViewDTO;
import osfix.ag.crm.service.mapper.factory.GoodsViewMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestProductViewMapper implements EntityMapper<RequestProduct, RequestProductViewDTO> {

    @Autowired
    private GoodsViewMapper goodsViewMapper;

    @Autowired
    private RequestViewMapper requestViewMapper;



    @Override
    public RequestProduct toEntity(RequestProductViewDTO dto) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setId(dto.getId());
        requestProduct.setQuantity(dto.getQuantity());
        requestProduct.setName(dto.getName());
        requestProduct.setPackaging(dto.getPackaging());
        //requestProduct.setRequest(requestService.findById(dto.getRequestId()));
        requestProduct.setStatus(dto.getStatus());
        requestProduct.setProductId(dto.getProductId());
        if(dto.getGoods() != null) {
            requestProduct.setGoods(goodsViewMapper.toEntity(dto.getGoods()));
        }
        return requestProduct;
    }

    @Override
    public RequestProductViewDTO fromEntity(RequestProduct entity) {
        RequestProductViewDTO dto = new RequestProductViewDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setName(entity.getName());
        dto.setPackaging(entity.getPackaging());
        dto.setProductId(entity.getProductId());
        if(entity.getRequest() != null) {
            dto.setRequestId(entity.getRequest().getId());
        }
        dto.setStatus(entity.getStatus());
        if(entity.getGoods() != null) {
            dto.setGoods(goodsViewMapper.fromEntity(entity.getGoods()));
        }
        return dto;
    }

    @Override
    public List<RequestProduct> fromDtoList(List<RequestProductViewDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestProductViewDTO> toDtoList(List<RequestProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

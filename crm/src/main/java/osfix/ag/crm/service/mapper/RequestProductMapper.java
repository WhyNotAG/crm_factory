package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestProductMapper implements EntityMapper<RequestProduct, RequestProductDTO> {

    @Autowired
    private RequestService requestService;
    @Override
    public RequestProduct toEntity(RequestProductDTO dto) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setId(dto.getId());
        requestProduct.setQuantity(dto.getQuantity());
        requestProduct.setName(dto.getName());
        requestProduct.setPackaging(dto.getPackaging());
        requestProduct.setRequest(requestService.findById(dto.getRequestId()));
        requestProduct.setStatus(dto.getStatus());
        return requestProduct;
    }

    @Override
    public RequestProductDTO fromEntity(RequestProduct entity) {
        RequestProductDTO dto = new RequestProductDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setName(entity.getName());
        dto.setPackaging(entity.getPackaging());
        dto.setRequestId(entity.getRequest().getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public List<RequestProduct> fromDtoList(List<RequestProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestProductDTO> toDtoList(List<RequestProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

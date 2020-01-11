package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.service.LepsariService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LepsariProductMapper implements EntityMapper<LepsariProduct, RequestProductDTO>{

    @Autowired
    private LepsariService lepsariService;

    @Override
    public LepsariProduct toEntity(RequestProductDTO dto) {
        LepsariProduct requestProduct = new LepsariProduct();
        requestProduct.setId(dto.getId());
        requestProduct.setQuantity(dto.getQuantity());
        requestProduct.setName(dto.getName());
        requestProduct.setPackaging(dto.getPackaging());
        requestProduct.setLepsari(lepsariService.findById(dto.getRequestId()));
        return requestProduct;
    }

    @Override
    public RequestProductDTO fromEntity(LepsariProduct entity) {
        RequestProductDTO dto = new RequestProductDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setName(entity.getName());
        dto.setPackaging(entity.getPackaging());
        dto.setRequestId(entity.getLepsari().getId());
        return dto;
    }

    @Override
    public List<LepsariProduct> fromDtoList(List<RequestProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestProductDTO> toDtoList(List<LepsariProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

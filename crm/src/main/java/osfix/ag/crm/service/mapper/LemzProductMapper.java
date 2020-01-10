package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LemzProductMapper implements EntityMapper<LemzProduct, RequestProductDTO> {
    @Autowired
    private LEMZService lemzService;

    @Override
    public LemzProduct toEntity(RequestProductDTO dto) {
        LemzProduct requestProduct = new LemzProduct();
        requestProduct.setId(dto.getId());
        requestProduct.setQuantity(dto.getQuantity());
        requestProduct.setName(dto.getName());
        requestProduct.setPackaging(dto.getPackaging());
        requestProduct.setLemz(lemzService.findById(dto.getRequestId()));
        return requestProduct;
    }

    @Override
    public RequestProductDTO fromEntity(LemzProduct entity) {
        RequestProductDTO dto = new RequestProductDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setName(entity.getName());
        dto.setPackaging(entity.getPackaging());
        dto.setRequestId(entity.getLemz().getId());
        return dto;
    }

    @Override
    public List<LemzProduct> fromDtoList(List<RequestProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestProductDTO> toDtoList(List<LemzProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
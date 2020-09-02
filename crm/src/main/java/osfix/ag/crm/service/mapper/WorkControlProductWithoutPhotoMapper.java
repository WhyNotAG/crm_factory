package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.service.dto.WorkControlProductWithoutPhotoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkControlProductWithoutPhotoMapper implements EntityMapper<WorkControlProduct, WorkControlProductWithoutPhotoDTO> {
    @Autowired
    ProductWithoutPhotoMapper productWithoutPhotoMapper;

    @Override
    public WorkControlProduct toEntity(WorkControlProductWithoutPhotoDTO dto) {
        WorkControlProduct workControlProduct = new WorkControlProduct();
        workControlProduct.setId(dto.getId());
        workControlProduct.setProduct(productWithoutPhotoMapper.toEntity(dto.getProduct()));
        workControlProduct.setQuantity(dto.getQuantity());
        workControlProduct.setWorkControl(dto.getWorkControl());
        return workControlProduct;
    }

    @Override
    public WorkControlProductWithoutPhotoDTO fromEntity(WorkControlProduct entity) {
        WorkControlProductWithoutPhotoDTO dto = new WorkControlProductWithoutPhotoDTO();
        dto.setId(entity.getId());
        dto.setProduct(productWithoutPhotoMapper.fromEntity(entity.getProduct()));
        dto.setQuantity(entity.getQuantity());
        dto.setWorkControl(entity.getWorkControl());
        return dto;
    }

    @Override
    public List<WorkControlProduct> fromDtoList(List<WorkControlProductWithoutPhotoDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkControlProductWithoutPhotoDTO> toDtoList(List<WorkControlProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

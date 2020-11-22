package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.ProductsWithoutPhotoDTO;
import osfix.ag.crm.service.dto.WorkControlProductDTO;
import osfix.ag.crm.service.dto.WorkControlProductWithoutPhotoDTO;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class WorkControlProductWithoutPhotoMapper implements EntityMapper<WorkControlProduct, WorkControlProductWithoutPhotoDTO> {
    @Autowired
    private WorkControlService workControlService;
    @Autowired
    private ProductWithoutPhotoMapper withoutPhotoMapper;

    @Override
    public WorkControlProduct toEntity(WorkControlProductWithoutPhotoDTO dto) {
        WorkControlProduct workControlProduct = new WorkControlProduct();
        workControlProduct.setId(dto.getId());
        workControlProduct.setProduct(withoutPhotoMapper.toEntity(dto.getProduct()));
        workControlProduct.setWorkControl(dto.getWorkControl());
        workControlProduct.setQuantity(dto.getQuantity());
        return workControlProduct;
    }

    @Override
    public WorkControlProductWithoutPhotoDTO fromEntity(WorkControlProduct entity) {
        WorkControlProductWithoutPhotoDTO dto = new WorkControlProductWithoutPhotoDTO();
        dto.setId(dto.getId());
        dto.setProduct(withoutPhotoMapper.fromEntity(entity.getProduct()));
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

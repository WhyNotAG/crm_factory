package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.WorkControlProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkControlProductMapper implements EntityMapper<WorkControlProduct, WorkControlProductDTO> {
    @Autowired
    private WorkControlService workControlService;

    @Override
    public WorkControlProduct toEntity(WorkControlProductDTO dto) {
        WorkControlProduct workControlProduct = new WorkControlProduct();
        workControlProduct.setId(dto.getId());
        workControlProduct.setProductId(dto.getProductId());
        workControlProduct.setWorkControl(workControlService.findById(dto.getWorkControlId()));
        workControlProduct.setQuantity(dto.getQuantity());
        return workControlProduct;
    }

    @Override
    public WorkControlProductDTO fromEntity(WorkControlProduct entity) {
        WorkControlProductDTO dto = new WorkControlProductDTO();
        dto.setId(dto.getId());
        dto.setProductId(dto.getProductId());
        dto.setWorkControlId(entity.getWorkControl().getId());
        dto.setQuantity(dto.getQuantity());
        return dto;
    }

    @Override
    public List<WorkControlProduct> fromDtoList(List<WorkControlProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkControlProductDTO> toDtoList(List<WorkControlProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

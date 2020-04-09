package osfix.ag.crm.service.mapper.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.EquipmentProduct;
import osfix.ag.crm.repo.factory.EquipmentRepo;
import osfix.ag.crm.service.dto.factory.EquipmentProductDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentProductMapper implements EntityMapper<EquipmentProduct, EquipmentProductDTO> {
    @Autowired
    EquipmentRepo equipmentRepo;

    @Override
    public EquipmentProduct toEntity(EquipmentProductDTO dto) {
        EquipmentProduct equipmentProduct = new EquipmentProduct();
        equipmentProduct.setEquipment(equipmentRepo.findById(dto.getEquipmentId()).orElse(null));
        equipmentProduct.setId(dto.getId());
        equipmentProduct.setName(dto.getName());
        equipmentProduct.setQuantity(dto.getQuantity());
        return equipmentProduct;
    }

    @Override
    public EquipmentProductDTO fromEntity(EquipmentProduct entity) {
        EquipmentProductDTO dto = new EquipmentProductDTO();
        dto.setEquipmentId(entity.getEquipment().getId());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    @Override
    public List<EquipmentProduct> fromDtoList(List<EquipmentProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentProductDTO> toDtoList(List<EquipmentProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

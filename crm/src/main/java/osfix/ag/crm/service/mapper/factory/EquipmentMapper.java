package osfix.ag.crm.service.mapper.factory;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Equipment;
import osfix.ag.crm.service.dto.factory.EquipmentDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentMapper implements EntityMapper<Equipment, EquipmentDTO> {
    @Override
    public Equipment toEntity(EquipmentDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setAssembly(dto.getAssembly());
        equipment.setDate(new Date(dto.getDate()*1000));
        equipment.setDeliverBy(new Date(dto.getDeliverBy()*1000));
        equipment.setFactoryName(dto.getFactoryName());
        equipment.setId(dto.getId());
        equipment.setName(dto.getName());
        equipment.setStatus(dto.getStatus());
        return equipment;
    }

    @Override
    public EquipmentDTO fromEntity(Equipment entity) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setAssembly(entity.getAssembly());
        dto.setDate(entity.getDate().getTime());
        dto.setDeliverBy(entity.getDeliverBy().getTime());
        dto.setFactoryName(entity.getFactoryName());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public List<Equipment> fromDtoList(List<EquipmentDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> toDtoList(List<Equipment> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

package osfix.ag.crm.service.mapper.factory;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.service.dto.factory.PackingDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PackingMapper implements EntityMapper<Packing, PackingDTO> {
    @Override
    public Packing toEntity(PackingDTO dto) {
        Packing packing = new Packing();
        packing.setId(dto.getId());
        packing.setComment(dto.getComment());
        packing.setName(dto.getName());
        packing.setQuantity(dto.getQuantity());
        packing.setSize(dto.getSize());
        return packing;
    }

    @Override
    public PackingDTO fromEntity(Packing entity) {
        PackingDTO dto = new PackingDTO();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setSize(entity.getSize());
        return dto;
    }

    @Override
    public List<Packing> fromDtoList(List<PackingDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PackingDTO> toDtoList(List<Packing> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

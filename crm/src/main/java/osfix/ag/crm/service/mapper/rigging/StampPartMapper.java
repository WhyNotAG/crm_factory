package osfix.ag.crm.service.mapper.rigging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.service.StampService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StampPartMapper implements EntityMapper<StampPart, RiggingPartDTO> {
    @Autowired
    private StampService stampService;

    @Override
    public StampPart toEntity(RiggingPartDTO dto) {
        StampPart stampPart = new StampPart();
        stampPart.setComment(dto.getComment());
        stampPart.setControll(dto.getControll());
        stampPart.setCuttingDimensions(dto.getCuttingDimensions());
        stampPart.setErosion(dto.getErosion());
        stampPart.setGrinding(dto.getGrinding());
        stampPart.setHarding(dto.getHarding());
        stampPart.setId(dto.getId());
        stampPart.setLocation(dto.getLocation());
        stampPart.setAmount(dto.getAmount());
        stampPart.setMilling(dto.getMilling());
        stampPart.setName(dto.getName());
        stampPart.setNumber(dto.getNumber());
        stampPart.setStamp(stampService.findById(dto.getRiggingId()));
        return stampPart;
    }

    @Override
    public RiggingPartDTO fromEntity(StampPart entity) {
        RiggingPartDTO dto = new RiggingPartDTO();
        dto.setComment(entity.getComment());
        dto.setControll(entity.getControll());
        dto.setCuttingDimensions(entity.getCuttingDimensions());
        dto.setErosion(entity.getErosion());
        dto.setAmount(entity.getAmount());
        dto.setGrinding(entity.getGrinding());
        dto.setHarding(entity.getHarding());
        dto.setId(entity.getId());
        dto.setLocation(entity.getLocation());
        dto.setMilling(entity.getMilling());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setRiggingId(entity.getStamp().getId());
        return dto;
    }

    @Override
    public List<StampPart> fromDtoList(List<RiggingPartDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiggingPartDTO> toDtoList(List<StampPart> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

package osfix.ag.crm.service.mapper.rigging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.service.PressService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PressPartMapper implements EntityMapper<PressPart, RiggingPartDTO> {
    @Autowired
    private PressService pressService;

    @Override
    public PressPart toEntity(RiggingPartDTO dto) {
        PressPart pressPart = new PressPart();
        pressPart.setComment(dto.getComment());
        pressPart.setControll(dto.getControll());
        pressPart.setCuttingDimensions(dto.getCuttingDimensions());
        pressPart.setErosion(dto.getErosion());
        pressPart.setGrinding(dto.getGrinding());
        pressPart.setHarding(dto.getHarding());
        pressPart.setId(dto.getId());
        pressPart.setLocation(dto.getLocation());
        pressPart.setMilling(dto.getMilling());
        pressPart.setName(dto.getName());
        pressPart.setNumber(dto.getNumber());
        pressPart.setPress(pressService.findById(dto.getRiggingId()));
        return pressPart;
    }

    @Override
    public RiggingPartDTO fromEntity(PressPart entity) {
        RiggingPartDTO dto = new RiggingPartDTO();
        dto.setComment(entity.getComment());
        dto.setControll(entity.getControll());
        dto.setCuttingDimensions(entity.getCuttingDimensions());
        dto.setErosion(entity.getErosion());
        dto.setGrinding(entity.getGrinding());
        dto.setHarding(entity.getHarding());
        dto.setId(entity.getId());
        dto.setLocation(entity.getLocation());
        dto.setMilling(entity.getMilling());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setRiggingId(entity.getPress().getId());
        return dto;
    }

    @Override
    public List<PressPart> fromDtoList(List<RiggingPartDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiggingPartDTO> toDtoList(List<PressPart> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

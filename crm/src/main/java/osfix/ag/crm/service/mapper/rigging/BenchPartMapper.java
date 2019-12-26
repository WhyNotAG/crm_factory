package osfix.ag.crm.service.mapper.rigging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.service.BenchService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BenchPartMapper implements EntityMapper<BenchPart, RiggingPartDTO> {
    @Autowired
    private BenchService benchService;

    @Override
    public BenchPart toEntity(RiggingPartDTO dto) {
        BenchPart benchPart = new BenchPart();
        benchPart.setComment(dto.getComment());
        benchPart.setControll(dto.getControll());
        benchPart.setCuttingDimensions(dto.getCuttingDimensions());
        benchPart.setErosion(dto.getErosion());
        benchPart.setGrinding(dto.getGrinding());
        benchPart.setHarding(dto.getHarding());
        benchPart.setAmount(dto.getAmount());
        benchPart.setId(dto.getId());
        benchPart.setLocation(dto.getLocation());
        benchPart.setMilling(dto.getMilling());
        benchPart.setName(dto.getName());
        benchPart.setNumber(dto.getNumber());
        benchPart.setBench(benchService.findById(dto.getRiggingId()));
        return benchPart;
    }

    @Override
    public RiggingPartDTO fromEntity(BenchPart entity) {
        RiggingPartDTO dto = new RiggingPartDTO();
        dto.setComment(entity.getComment());
        dto.setControll(entity.getControll());
        dto.setCuttingDimensions(entity.getCuttingDimensions());
        dto.setErosion(entity.getErosion());
        dto.setGrinding(entity.getGrinding());
        dto.setAmount(entity.getAmount());
        dto.setHarding(entity.getHarding());
        dto.setId(entity.getId());
        dto.setLocation(entity.getLocation());
        dto.setMilling(entity.getMilling());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setRiggingId(entity.getBench().getId());
        return dto;
    }

    @Override
    public List<BenchPart> fromDtoList(List<RiggingPartDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiggingPartDTO> toDtoList(List<BenchPart> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

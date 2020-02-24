package osfix.ag.crm.service.mapper.rigging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;
import osfix.ag.crm.service.DetailService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailPartMapper implements EntityMapper<DetailPart, RiggingPartDTO> {
    @Autowired
    private DetailService detailService;

    @Override
    public DetailPart toEntity(RiggingPartDTO dto) {
        DetailPart detailPart = new DetailPart();
        detailPart.setComment(dto.getComment());
        detailPart.setControll(dto.getControll());
        detailPart.setCuttingDimensions(dto.getCuttingDimensions());
        detailPart.setErosion(dto.getErosion());
        detailPart.setGrinding(dto.getGrinding());
        detailPart.setHarding(dto.getHarding());
        detailPart.setAmount(dto.getAmount());
        detailPart.setId(dto.getId());
        detailPart.setLocation(dto.getLocation());
        detailPart.setMilling(dto.getMilling());
        detailPart.setName(dto.getName());
        detailPart.setNumber(dto.getNumber());
        detailPart.setColor(dto.getColor());
        detailPart.setDetail(detailService.findById(dto.getRiggingId()));
        detailPart.setDrawing(dto.getDrawing());
        return detailPart;
    }

    @Override
    public RiggingPartDTO fromEntity(DetailPart entity) {
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
        dto.setRiggingId(entity.getDetail().getId());
        dto.setColor(entity.getColor());
        dto.setDrawing(entity.getDrawing());
        return dto;
    }

    @Override
    public List<DetailPart> fromDtoList(List<RiggingPartDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RiggingPartDTO> toDtoList(List<DetailPart> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

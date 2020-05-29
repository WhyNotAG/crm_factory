package osfix.ag.crm.service.mapper.factory;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.dispatcher.rigging.parts.*;
import osfix.ag.crm.service.dto.factory.PartsDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
@Component
public class PartsMapper {

    public Part toEntity(PartsDTO dto) {
        Part part = null;

        if(dto.getPartsType().equals("Stamp")){
            part = new StampPart();
        }

        if(dto.getPartsType().equals("Bench")){
            part = new BenchPart();
        }

        if(dto.getPartsType().equals("Press")){
            part = new PressPart();
        }

        if(dto.getPartsType().equals("Detail")){
            part = new DetailPart();
        }

        part.setAmount(dto.getAmount());
        part.setColor(dto.getColor());
        part.setComment(dto.getComment());
        part.setControll(dto.getControll());
        part.setCuttingDimensions(dto.getCuttingDimensions());
        part.setDrawing(dto.getDrawing());
        part.setErosion(dto.getErosion());
        part.setGrinding(dto.getGrinding());
        part.setHarding(dto.getHarding());
        part.setId(dto.getId());
        part.setLocation(dto.getLocation());
        part.setMilling(dto.getMilling());
        part.setName(dto.getName());
        part.setNumber(dto.getNumber());

        return part;
    }

    public PartsDTO fromEntity(Part entity, String type, Long quantity) {
        PartsDTO dto = new PartsDTO();
        dto.setAmount(entity.getAmount());
        dto.setColor(entity.getColor());
        dto.setComment(entity.getComment());
        dto.setControll(entity.getControll());
        dto.setCuttingDimensions(entity.getCuttingDimensions());
        dto.setDrawing(entity.getDrawing());
        dto.setErosion(entity.getErosion());
        dto.setGrinding(entity.getGrinding());
        dto.setHarding(entity.getHarding());
        dto.setId(entity.getId());
        dto.setLocation(entity.getLocation());
        dto.setMilling(entity.getMilling());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setQuantity(quantity);
        dto.setPartsType(type);
        return dto;
    }

    public List<Part> fromDtoList(List<PartsDTO> list) {
        return null;
    }

    public List<PartsDTO> toDtoList(List<Part> list) {
        return null;
    }
}

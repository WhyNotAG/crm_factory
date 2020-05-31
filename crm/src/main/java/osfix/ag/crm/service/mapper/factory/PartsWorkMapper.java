package osfix.ag.crm.service.mapper.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;
import osfix.ag.crm.repo.WorkControlRepo;
import osfix.ag.crm.repo.rigging.part.BenchPartRepo;
import osfix.ag.crm.repo.rigging.part.DetailPartRepo;
import osfix.ag.crm.repo.rigging.part.PressPartRepo;
import osfix.ag.crm.repo.rigging.part.StampPartRepo;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.dto.factory.PartsWorkDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartsWorkMapper implements EntityMapper<PartsWork, PartsWorkDTO> {
    @Autowired
    WorkControlRepo workControlRepo;

    @Autowired
    DetailPartRepo detailPartRepo;
    @Autowired
    StampPartRepo stampPartRepo;
    @Autowired
    BenchPartRepo benchPartRepo;
    @Autowired
    PressPartRepo pressPartRepo;


    @Override
    public PartsWork toEntity(PartsWorkDTO dto) {
        PartsWork partsWork = new PartsWork();
        partsWork.setId(dto.getId());
        partsWork.setPartId(dto.getPartId());
        partsWork.setPartType(dto.getPartType());
        partsWork.setQuantity(dto.getQuantity());
        partsWork.setName(dto.getName());
        partsWork.setWorkControl(workControlRepo.findById(dto.getWorkControl()).orElse(null));
        return partsWork;
    }

    @Override
    public PartsWorkDTO fromEntity(PartsWork entity) {
        PartsWorkDTO dto = new PartsWorkDTO();
        dto.setId(entity.getId());
        dto.setPartId(entity.getPartId());
        dto.setPartType(entity.getPartType());
        dto.setQuantity(entity.getQuantity());

        if (entity.getPartType().equals("Stamp")) {
           dto.setName(stampPartRepo.findById(entity.getPartId()).orElse(null).getName());
        }
        if (entity.getPartType().equals("Bench")) {
            dto.setName(stampPartRepo.findById(entity.getPartId()).orElse(null).getName());
        }
        if (entity.getPartType().equals("Detail")) {
            dto.setName(stampPartRepo.findById(entity.getPartId()).orElse(null).getName());
        }
        if (entity.getPartType().equals("Press")) {
            dto.setName(stampPartRepo.findById(entity.getPartId()).orElse(null).getName());
        }

        dto.setWorkControl(entity.getWorkControl().getId());
        return dto;
    }

    @Override
    public List<PartsWork> fromDtoList(List<PartsWorkDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartsWorkDTO> toDtoList(List<PartsWork> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

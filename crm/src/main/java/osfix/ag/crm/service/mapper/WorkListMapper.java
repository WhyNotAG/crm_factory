package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.service.dto.WorkListDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkListMapper implements EntityMapper<WorkList, WorkListDTO> {
    @Override
    public WorkList toEntity(WorkListDTO dto) {
        WorkList workList = new WorkList();
        workList.setId(dto.getId());
        workList.setWork(dto.getWork());
        return workList;
    }

    @Override
    public WorkListDTO fromEntity(WorkList entity) {
        WorkListDTO dto = new WorkListDTO();
        dto.setId(entity.getId());
        dto.setWork(dto.getWork());
        return dto;
    }

    @Override
    public List<WorkList> fromDtoList(List<WorkListDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkListDTO> toDtoList(List<WorkList> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

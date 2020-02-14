package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.service.EmployeeService;
import osfix.ag.crm.service.WorkListService;
import osfix.ag.crm.service.dto.WorkControlDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkControlMapper implements EntityMapper<WorkControl, WorkControlDTO> {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private WorkListService workListService;
    @Override
    public WorkControl toEntity(WorkControlDTO dto) {
        WorkControl workControl = new WorkControl();
        workControl.setEmployee(employeeService.findById(dto.getEmployeeId()));
        workControl.setDay(dto.getDay());
        workControl.setHours(dto.getHours());
        workControl.setId(dto.getId());
        workControl.setMonth(dto.getMonth());
        workControl.setWorkList(workListService.findById(dto.getWorkListId()));
        workControl.setYear(dto.getYear());
        return workControl;
    }

    @Override
    public WorkControlDTO fromEntity(WorkControl entity) {
        WorkControlDTO dto = new WorkControlDTO();
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setDay(entity.getDay());
        dto.setHours(entity.getHours());
        dto.setId(entity.getId());
        dto.setMonth(entity.getMonth());
        dto.setWorkListId(entity.getWorkList().getId());
        dto.setYear(entity.getYear());
        return dto;
    }

    @Override
    public List<WorkControl> fromDtoList(List<WorkControlDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkControlDTO> toDtoList(List<WorkControl> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.service.dto.EmployeeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper implements EntityMapper<Employee, EmployeeDTO> {
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setCitizenship(dto.getCitizenship());
        employee.setComment(dto.getComment());
        employee.setId(dto.getId());
        employee.setLastName(dto.getLastName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setName(dto.getName());
        employee.setPatent(dto.getPatent());
        employee.setPosition(dto.getPosition());
        employee.setRelevance(dto.getRelevance());
        employee.setWorkshop(dto.getWorkshop());
        employee.setYearOfBirth(dto.getYearOfBirth());
        return employee;
    }

    @Override
    public EmployeeDTO fromEntity(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setCitizenship(entity.getCitizenship());
        dto.setComment(entity.getComment());
        dto.setId(entity.getId());
        dto.setLastName(entity.getLastName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setName(entity.getName());
        dto.setPatent(entity.getPatent());
        dto.setPosition(entity.getPosition());
        dto.setRelevance(entity.getRelevance());
        dto.setWorkshop(entity.getWorkshop());
        dto.setYearOfBirth(entity.getYearOfBirth());
        return dto;
    }

    @Override
    public List<Employee> fromDtoList(List<EmployeeDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> toDtoList(List<Employee> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

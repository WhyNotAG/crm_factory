package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.service.dto.EmployeeDownloadDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDownloadMapper implements EntityMapper<Employee, EmployeeDownloadDTO>{
    @Override
        public Employee toEntity(EmployeeDownloadDTO dto) {
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
        if(dto.getDateOfBirth() != null)
            employee.setDateOfBirth(new Date(dto.getDateOfBirth().getTime()));
        if(dto.getPatentExpirationDate() != null && dto.getRegistrationExpirationDate() != null ) {
            employee.setPatentExpirationDate(new Date(dto.getPatentExpirationDate().getTime()));
            employee.setRegistrationExpirationDate(new Date(dto.getRegistrationExpirationDate().getTime()));
        }
        employee.setYearOfBirth(dto.getYearOfBirth());
        return employee;
    }

    @Override
    public EmployeeDownloadDTO fromEntity(Employee entity) {
        EmployeeDownloadDTO dto = new EmployeeDownloadDTO();
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
        if (entity.getDateOfBirth() != null)
            dto.setDateOfBirth(entity.getDateOfBirth());
        if (entity.getPatentExpirationDate() != null && entity.getRegistrationExpirationDate() != null) {
            dto.setPatentExpirationDate(entity.getPatentExpirationDate());
            dto.setRegistrationExpirationDate(entity.getRegistrationExpirationDate());
        }
        dto.setYearOfBirth(entity.getYearOfBirth());
        return dto;
    }

    @Override
    public List<Employee> fromDtoList(List<EmployeeDownloadDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDownloadDTO> toDtoList(List<Employee> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

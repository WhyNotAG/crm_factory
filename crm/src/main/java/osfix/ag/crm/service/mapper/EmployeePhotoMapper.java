package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.domain.EmployeePhoto;
import osfix.ag.crm.service.dto.EmployeeDTO;
import osfix.ag.crm.service.dto.EmployeePhotoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeePhotoMapper implements EntityMapper<EmployeePhoto, EmployeePhotoDTO>{
    @Override
    public EmployeePhoto toEntity(EmployeePhotoDTO dto) {
        EmployeePhoto employeePhoto = new EmployeePhoto();
        employeePhoto.setUrl(dto.getUrl());
        employeePhoto.setId(dto.getId());
        return employeePhoto;
    }

    @Override
    public EmployeePhotoDTO fromEntity(EmployeePhoto entity) {
        EmployeePhotoDTO dto = new EmployeePhotoDTO();
        dto.setId(entity.getId());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    @Override
    public List<EmployeePhoto> fromDtoList(List<EmployeePhotoDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeePhotoDTO> toDtoList(List<EmployeePhoto> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

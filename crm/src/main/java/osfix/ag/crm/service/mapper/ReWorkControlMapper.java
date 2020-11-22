package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.repo.WorkControlRepo;
import osfix.ag.crm.repo.product.WorkControlProductRepo;
import osfix.ag.crm.repo.rigging.part.PartsWorkRepo;
import osfix.ag.crm.service.dto.ReWorkControlDTO;
import osfix.ag.crm.service.dto.WorkControlProductWithoutPhotoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReWorkControlMapper implements EntityMapper<WorkControl, ReWorkControlDTO> {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    WorkControlProductRepo workControlProductRepo;

    @Autowired
    PartsWorkRepo partsWorkRepo;

    @Autowired
    private WorkControlProductWithoutPhotoMapper workControlProductWithoutPhotoMapper;

    @Override
    public WorkControl toEntity(ReWorkControlDTO dto) {
        WorkControl workControl = new WorkControl();
        workControl.setWorkControlProduct(workControlProductWithoutPhotoMapper.fromDtoList(dto.getWorkControlProduct()));
        workControl.setPartsWork(dto.getPartsWorks());
        workControl.setYear(dto.getYear());
        workControl.setWorkList(dto.getWorkList());
        workControl.setMonth(dto.getMonth());
        workControl.setId(dto.getId());
        workControl.setHours(dto.getHours());
        workControl.setDay(dto.getDay());
        workControl.setEmployee(employeeMapper.toEntity(dto.getEmployee()));
        workControl.setComments(dto.getComment());
        return workControl;
    }

    @Override
    public ReWorkControlDTO fromEntity(WorkControl entity) {
        ReWorkControlDTO dto = new ReWorkControlDTO();
        if (entity.getWorkControlProduct() != null) { dto.setWorkControlProduct(workControlProductWithoutPhotoMapper.toDtoList(entity.getWorkControlProduct())); }
            else {
                List<WorkControlProductWithoutPhotoDTO> products = workControlProductWithoutPhotoMapper.toDtoList(workControlProductRepo.findAllByWorkControl(entity));
                dto.setWorkControlProduct(products);
        }

        if (entity.getPartsWork() != null) { dto.setPartsWorks(entity.getPartsWork()); }
        else {
            List<WorkControlProductWithoutPhotoDTO> products =workControlProductWithoutPhotoMapper.toDtoList(workControlProductRepo.findAllByWorkControl(entity));
            dto.setWorkControlProduct(products);
        }
        dto.setYear(entity.getYear());
        dto.setWorkList(entity.getWorkList());
        dto.setMonth(entity.getMonth());
        dto.setId(entity.getId());
        dto.setHours(entity.getHours());
        dto.setDay(entity.getDay());
        dto.setEmployee(employeeMapper.fromEntity(entity.getEmployee()));
        dto.setComment(entity.getComments());
        return dto;
    }

    @Override
    public List<WorkControl> fromDtoList(List<ReWorkControlDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReWorkControlDTO> toDtoList(List<WorkControl> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

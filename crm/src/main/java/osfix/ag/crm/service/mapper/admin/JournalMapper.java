package osfix.ag.crm.service.mapper.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.admin.Journal;
import osfix.ag.crm.service.EmployeeService;
import osfix.ag.crm.service.dto.admin.JournalDTO;
import osfix.ag.crm.service.mapper.EmployeeMapper;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JournalMapper implements EntityMapper<Journal, JournalDTO> {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Journal toEntity(JournalDTO dto) {
        Journal journal = new Journal();
        journal.setId(dto.getId());
        journal.setComment(dto.getComment());
        journal.setEmployee(employeeService.findById(dto.getEmployeeId()));
        if(dto.getEmployeeDTO() != null) {
            journal.setEmployee(employeeMapper.toEntity(dto.getEmployeeDTO()));
        }
        Timestamp ts=new Timestamp(dto.getDate().getTime() * 1000);
        journal.setDate(new Date(ts.getTime()));
        return journal;
    }

    @Override
    public JournalDTO fromEntity(Journal entity) {
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setId(entity.getId());
        journalDTO.setComment(entity.getComment());
        if(entity.getEmployee() != null) {
            journalDTO.setEmployeeDTO(employeeMapper.fromEntity(entity.getEmployee()));
        }
        journalDTO.setEmployeeId(entity.getEmployee().getId());
        journalDTO.setDate(entity.getDate());
        return journalDTO;
    }

    @Override
    public List<Journal> fromDtoList(List<JournalDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<JournalDTO> toDtoList(List<Journal> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

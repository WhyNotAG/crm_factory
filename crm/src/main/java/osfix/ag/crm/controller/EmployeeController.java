package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.service.EmployeeService;
import osfix.ag.crm.service.dto.EmployeeDTO;
import osfix.ag.crm.service.mapper.EmployeeMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok().body(employeeMapper.toDtoList(employeeService.findAll()));
    }

    @PostMapping("/workshop")
    public ResponseEntity<List<EmployeeDTO>> findByWorkshop(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok().body(employeeMapper.toDtoList(employeeService.findByWorkshop(employeeDTO.getWorkshop())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(employeeMapper.fromEntity(employeeService.findById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> add(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeMapper.fromEntity(employeeService.save(employee)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeMapper.fromEntity(employeeService.update(id, employee)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        employeeService.delete(id);
    }
}

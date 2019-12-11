package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) { this.employeeService = employeeService; }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        employeeService.delete(id);
    }
}

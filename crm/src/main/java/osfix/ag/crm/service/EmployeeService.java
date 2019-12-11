package osfix.ag.crm.service;

import osfix.ag.crm.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee update(Long id, Employee employee);
    Employee save(Employee employee);
    void delete(Long id);
}

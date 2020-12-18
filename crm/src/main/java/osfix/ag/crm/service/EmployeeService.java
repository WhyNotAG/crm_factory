package osfix.ag.crm.service;

import osfix.ag.crm.domain.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee update(Long id, Employee employee);
    Employee save(Employee employee);
    List<Employee> findByWorkshop(String workshop);
    List<Employee> findBirth();
    Set<Employee> findAllByPatentOrRegistration();
    void delete(Long id);
}

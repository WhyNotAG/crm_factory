package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.repo.EmployeeRepo;
import osfix.ag.crm.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) { this.employeeRepo = employeeRepo; }

    @Override
    public List<Employee> findAll() { return employeeRepo.findAll(); }

    @Override
    public Employee findById(Long id) { return employeeRepo.findById(id).orElse(null); }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeFromDb = employeeRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(employee, employeeFromDb, "id");
        employeeRepo.save(employeeFromDb);
        return employeeFromDb;
    }

    @Override
    public Employee save(Employee employee) { return employeeRepo.save(employee); }

    @Override
    public List<Employee> findByWorkshop(String workshop) {
        List<Employee> employees = employeeRepo.findAllByWorkshop(workshop);
        return employees;
    }

    @Override
    public void delete(Long id) { employeeRepo.deleteById(id); }
}

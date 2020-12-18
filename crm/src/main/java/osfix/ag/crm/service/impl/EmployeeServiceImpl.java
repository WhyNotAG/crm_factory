package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.repo.EmployeeRepo;
import osfix.ag.crm.service.EmployeeService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public List<Employee> findBirth() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Employee> employees = employeeRepo.findAll();
        List<Employee> result = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for(Employee employee : employees) {
            cal.setTimeInMillis(employee.getDateOfBirth().getTime());
            if(cal.get(Calendar.DAY_OF_MONTH) == localDateTime.getDayOfMonth() && cal.get(Calendar.MONTH)+1 == localDateTime.getMonthValue()) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public void delete(Long id) { employeeRepo.deleteById(id); }
}

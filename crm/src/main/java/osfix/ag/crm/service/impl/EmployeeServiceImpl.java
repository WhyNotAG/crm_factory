package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.controller.FileControllerWithoutDB;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.domain.EmployeePhoto;
import osfix.ag.crm.repo.EmployeePhotoRepo;
import osfix.ag.crm.repo.EmployeeRepo;
import osfix.ag.crm.service.EmployeeService;
import osfix.ag.crm.service.FileStorageService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;
    private FileStorageService fileStorageService;
    private EmployeePhotoRepo employeePhotoRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, FileStorageService fileStorageService, EmployeePhotoRepo employeePhotoRepo) {
        this.employeeRepo = employeeRepo;
        this.fileStorageService = fileStorageService;
        this.employeePhotoRepo = employeePhotoRepo;
    }

    @Override
    public List<Employee> findAll() { return employeeRepo.findAll(); }

    @Override
    public Employee findById(Long id) { return employeeRepo.findById(id).orElse(null); }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeFromDb = employeeRepo.findById(id).orElse(null);
        for (EmployeePhoto employeePhoto : employeeFromDb.getEmployeePhotos()) {
            employeePhotoRepo.delete(employeePhoto);
            fileStorageService.deleteFileWithUri(employeePhoto.getUrl());
        }
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
            if (employee.getDateOfBirth() == null) continue;
            cal.setTimeInMillis(employee.getDateOfBirth().getTime());
            if(cal.get(Calendar.DAY_OF_MONTH) <= localDateTime.getDayOfMonth() + 2 &&
                    cal.get(Calendar.DAY_OF_MONTH) >= localDateTime.getDayOfMonth() - 2 &&
                    cal.get(Calendar.MONTH)+1 == localDateTime.getMonthValue()) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public Set<Employee> findAllByPatentOrRegistration() {
        LocalDateTime localDateTime = LocalDateTime.now();

        return employeeRepo.findAllByRegistrationExpirationDateBetweenOrPatentExpirationDateBetweenOrRegistrationExpirationDateIsNullAndCitizenshipNotContainsOrPatentExpirationDateIsNullAndCitizenshipNotContains(
                java.sql.Date.valueOf(localDateTime.minusWeeks(53).toLocalDate()),
                java.sql.Date.valueOf(localDateTime.plusWeeks(1).toLocalDate()),
                java.sql.Date.valueOf(localDateTime.minusWeeks(53).toLocalDate()),
                java.sql.Date.valueOf(localDateTime.plusWeeks(1).toLocalDate()),
                "РФ", "РФ");
    }

    @Override
    public void delete(Long id) { employeeRepo.deleteById(id); }
}

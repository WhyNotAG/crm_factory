package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.domain.TrashBase;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.repo.EmployeeRepo;
import osfix.ag.crm.repo.TrashBaseRepo;
import osfix.ag.crm.repo.WorkControlRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.WorkControlProductService;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.DayDTO;
import osfix.ag.crm.service.dto.WorkReportDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkControlServiceImpl implements WorkControlService {
    private WorkControlRepo workControlRepo;
    private EmployeeRepo employeeRepo;
    private ProductRepo productRepo;
    private WorkControlProductService workControlProductRepo;
    private TrashBaseRepo trashBaseRepo;

    public WorkControlServiceImpl(WorkControlRepo workControlRepo, EmployeeRepo employeeRepo,
                                  ProductRepo productRepo, WorkControlProductService workControlProductRepo,
                                  TrashBaseRepo trashBaseRepo) {
        this.workControlRepo = workControlRepo;
        this.employeeRepo = employeeRepo;
        this.productRepo = productRepo;
        this.workControlProductRepo = workControlProductRepo;
        this.trashBaseRepo = trashBaseRepo;
    }

    @Override
    public List<WorkControl> findAll() {
        return workControlRepo.findAll();
    }

    @Override
    public List<WorkControl> findAllByEmployeeId(Long id) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        return workControlRepo.findAllByEmployee(employee);
    }

    @Override
    public WorkControl save(WorkControl workControl) {
        return workControlRepo.save(workControl);
    }

    @Override
    public WorkControl update(Long id, WorkControl workControl) {
        WorkControl workControlFromDb = workControlRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(workControl, workControlFromDb, "id");
        return workControlRepo.save(workControlFromDb);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        workControlProductRepo.deleteAllByWorkControl(workControl);
        workControlRepo.deleteById(id);
    }

    @Override
    public WorkControl findById(Long id) {
        return workControlRepo.findById(id).orElse(null);
    }

    @Override
    public List<WorkControl> findAllByEmployeeAndYearAndMonthAndDay(Long id, Integer year, Integer month, Integer day) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        return workControlRepo.findAllByEmployeeAndYearAndMonthAndDay(employee, year, month, day);
    }

    @Override
    public List<WorkControl> findAllByMonth(Integer month, Integer year) {
        return workControlRepo.findAllByMonthAndYear(month, year);
    }

    @Override
    public WorkReportDTO reportByEmployeeId(Long id, Integer month, Integer year) {

        Employee employee = employeeRepo.findById(id).orElse(null);
        WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setEmployee(employee);

        List<DayDTO> days = new ArrayList<>();
        DayDTO day = new DayDTO();

        List<WorkControl> byEmployee = workControlRepo.findAllByEmployeeAndMonthAndYear(employee, month, year);
        byEmployee.sort((o1, o2) -> o1.getDay().compareTo(o2.getDay()));
        Integer temp;

        for (WorkControl workControl : byEmployee) {
            temp = workControl.getDay();
            if(temp.equals(day.getDay())) {
                day.setDay(temp);
                if(day.getHours() != null) { day.setHours(day.getHours() + workControl.getHours()); }
                else {day.setHours(workControl.getHours());}
            } else {
                if (day.getDay() != null){days.add(day);}
                day = new DayDTO();
                day.setDay(temp);
                if(day.getHours() != null) { day.setHours(day.getHours() + workControl.getHours()); }
                else {day.setHours(workControl.getHours());}
            }
        }

        days.add(day);
        workReportDTO.setDays(days);
        return workReportDTO;
    }

    @Override
    public WorkControl addProduct(Long id, Long product_id, Long quantity, String name) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        Product product = productRepo.findById(product_id).orElse(null);

        if (product == null) {
            TrashBase trashBase = new TrashBase();
            trashBase.setName(name);
            trashBase.setQuantity(quantity);
            trashBase.setWorkControl(workControl);
            trashBaseRepo.save(trashBase);
            return null;
        }

        List<WorkControlProduct> workControlProducts = workControl.getWorkControlProduct();
        WorkControlProduct workControlProduct = new WorkControlProduct();
        workControlProduct.setQuantity(quantity);
        workControlProduct.setWorkControl(workControl);
        workControlProduct.setProduct(product);
        workControlProductRepo.save(workControlProduct);
        workControlProducts.add(workControlProductRepo.save(workControlProduct));

        return workControlRepo.save(workControl);
    }

    @Override
    public WorkControl deleteProduct(Long id, Long product_id) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        WorkControlProduct workControlProduct = workControlProductRepo.findByWorkAndProduct(workControl, product_id);
        workControlProductRepo.delete(workControlProduct.getId());
        return workControlRepo.save(workControl);
    }

    @Override
    public List<WorkControl> findByDayAndMonth(Integer day, Integer month, Integer year) {
        return workControlRepo.findAllByDayAndMonthAndYear(day, month, year);
    }

    @Override
    public List<WorkControl> findByRange(Integer dayFirst, Integer monthFirst, Integer dayLast, Integer monthLast,
                                         Integer yearFirst, Integer yearLast ) {
        List<List<WorkControl>> workControls = new ArrayList<>(Math.abs(monthLast - monthFirst));

        for (int i = monthFirst + 1; i < monthLast; i++) {
            workControls.add(workControlRepo.findAllByMonth(i));
        }

        List<WorkControl> workControlsFirst = workControlRepo.findAllByMonthAndYear(monthFirst, yearFirst);
        List<WorkControl> workControlsLast = workControlRepo.findAllByMonthAndYear(monthLast, yearLast);
        List<WorkControl> result = new ArrayList<>();

        if (monthFirst != monthLast) {
            for(WorkControl workControl : workControlsFirst) {
                if(workControl.getDay() >= dayFirst) { result.add(workControl); }
            }

            for(List<WorkControl> workControls1 : workControls) {
                for(WorkControl workControl : workControls1) { result.add(workControl); }
        }

            for (WorkControl workControl : workControlsLast) {
                if (workControl.getDay() <= dayLast) { result.add(workControl); }
            }
        } else {
            for (WorkControl workControl : workControlsFirst) {
                if (workControl.getDay() <= dayLast && workControl.getDay() >= dayFirst) { result.add(workControl); }
            }
        }

        return result;
    }

    @Override
    public WorkControl deleteProductV2(Long id, Long product_id) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        workControlProductRepo.delete(product_id);
        return workControl;
    }
}

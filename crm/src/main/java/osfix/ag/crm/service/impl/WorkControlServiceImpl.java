package osfix.ag.crm.service.impl;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.WorkControlProduct;
import osfix.ag.crm.repo.EmployeeRepo;
import osfix.ag.crm.repo.WorkControlRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.WorkControlProductService;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.DayDTO;
import osfix.ag.crm.service.dto.WorkReportDTO;

import java.util.ArrayList;
import java.util.List;
@Service
public class WorkControlServiceImpl implements WorkControlService {
    private WorkControlRepo workControlRepo;
    private EmployeeRepo employeeRepo;
    private ProductRepo productRepo;
    private WorkControlProductService workControlProductRepo;

    public WorkControlServiceImpl(WorkControlRepo workControlRepo, EmployeeRepo employeeRepo,
                                  ProductRepo productRepo, WorkControlProductService workControlProductRepo) {
        this.workControlRepo = workControlRepo;
        this.employeeRepo = employeeRepo;
        this.productRepo = productRepo;
        this.workControlProductRepo = workControlProductRepo;
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
        return null;
    }

    @Override
    public void delete(Long id) {
        workControlRepo.deleteById(id);
    }

    @Override
    public WorkControl findById(Long id) {
        return workControlRepo.findById(id).orElse(null);
    }

    @Override
    public List<WorkControl> findAllByMonth(Integer month) {
        return workControlRepo.findAllByMonth(month);
    }

    @Override
    public WorkReportDTO reportByEmployeeId(Long id, Integer month) {

        Employee employee = employeeRepo.findById(id).orElse(null);
        WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setEmployee(employee);

        List<DayDTO> days = new ArrayList<>();
        DayDTO day = new DayDTO();

        List<WorkControl> byEmployee = workControlRepo.findAllByEmployeeAndMonth(employee, month);
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
    public WorkControl addProduct(Long id, Long product_id, Long quantity) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        List<Product> products = workControl.getProducts();

        List<WorkControlProduct> workControlProducts = workControl.getWorkControlProduct();
        WorkControlProduct workControlProduct = new WorkControlProduct();
        workControlProduct.setQuantity(quantity);
        workControlProduct.setWorkControl(workControl);
        workControlProduct.setProductId(product_id);
        workControlProducts.add(workControlProductRepo.save(workControlProduct));

        products.add(productRepo.findById(product_id).orElse(null));
        workControl.setProducts(products);
        workControl.setWorkControlProduct(workControlProducts);
        return workControlRepo.save(workControl);
    }

    @Override
    public WorkControl deleteProduct(Long id, Long product_id) {
        WorkControl workControl = workControlRepo.findById(id).orElse(null);
        List<Product> products = workControl.getProducts();
        products.remove(productRepo.findById(product_id).orElse(null));
        workControl.setProducts(products);

        List<WorkControlProduct> workControlProducts = workControl.getWorkControlProduct();
        workControlProducts.remove(workControlProductRepo.findByWorkAndProduct(workControl,product_id));
        workControl.setWorkControlProduct(workControlProducts);

        return workControlRepo.save(workControl);
    }

    @Override
    public List<WorkControl> reportByMonth(String month) {
        return null;
    }
}

package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Employee;
import osfix.ag.crm.domain.WorkControl;

import java.util.List;

@Repository
public interface WorkControlRepo extends JpaRepository<WorkControl, Long> {
    List<WorkControl> findAllByMonth(Integer month);
    List<WorkControl> findAllByEmployee(Employee employee);
    List<WorkControl> findAllByDay(Integer day);
    List<WorkControl> findAllByEmployeeAndMonth(Employee employee, Integer month);

}
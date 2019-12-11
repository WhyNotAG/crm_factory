package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}

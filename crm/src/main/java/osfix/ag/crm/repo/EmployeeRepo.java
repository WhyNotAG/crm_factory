package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Employee;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findAllByWorkshop(String workshop);
    Set<Employee> findAllByRegistrationExpirationDateBetweenOrPatentExpirationDateBetweenOrRegistrationExpirationDateIsNullAndCitizenshipNotContainsOrPatentExpirationDateIsNullAndCitizenshipNotContains(Date regFrom, Date regTo, Date patFrom, Date patTo, String city, String city2);

}

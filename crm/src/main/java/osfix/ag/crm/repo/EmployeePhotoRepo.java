package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.EmployeePhoto;

@Repository
public interface EmployeePhotoRepo extends JpaRepository<EmployeePhoto, Long> {
}

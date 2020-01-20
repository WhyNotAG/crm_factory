package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.StorageLepsari;

@Repository
public interface StorageLepsariRepo extends JpaRepository<StorageLepsari, Long> {
}

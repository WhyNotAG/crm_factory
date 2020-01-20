package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.StorageLemz;

@Repository
public interface StorageLemzRepo extends JpaRepository<StorageLemz, Long> {
}

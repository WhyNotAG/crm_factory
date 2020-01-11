package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Storage;
@Repository
public interface StorageRepo extends JpaRepository<Storage, Long> {
}

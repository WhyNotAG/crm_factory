package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.TrashBase;

import javax.persistence.Entity;

@Repository
public interface TrashBaseRepo extends JpaRepository<TrashBase, Long> {
}

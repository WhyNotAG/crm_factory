package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.WorkList;

@Repository
public interface WorkListRepo extends JpaRepository<WorkList, Long> {
}

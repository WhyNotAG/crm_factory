package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Request;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
}

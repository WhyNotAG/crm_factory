package osfix.ag.crm.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.Request;

public interface RequestRepo extends JpaRepository<Request, Long> {
}

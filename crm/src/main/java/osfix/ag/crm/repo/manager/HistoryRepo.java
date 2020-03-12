package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.manager.History;

public interface HistoryRepo extends JpaRepository<History, Long> {

}

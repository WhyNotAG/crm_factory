package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.MainTask;

@Repository
public interface MainTaskRepo extends JpaRepository<MainTask, Long> {
}

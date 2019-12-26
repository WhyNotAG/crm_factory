package osfix.ag.crm.repo.rigging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.rigging.Press;

@Repository
public interface PressRepo extends JpaRepository<Press,Long> {
}

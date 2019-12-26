package osfix.ag.crm.repo.rigging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;

@Repository
public interface StampRepo extends JpaRepository<Stamp, Long> {
}

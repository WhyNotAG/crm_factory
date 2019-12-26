package osfix.ag.crm.repo.rigging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.rigging.Bench;

@Repository
public interface BenchRepo extends JpaRepository<Bench, Long> {
}

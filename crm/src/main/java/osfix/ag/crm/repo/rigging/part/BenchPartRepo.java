package osfix.ag.crm.repo.rigging.part;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;

public interface BenchPartRepo extends JpaRepository<BenchPart,Long> {
}

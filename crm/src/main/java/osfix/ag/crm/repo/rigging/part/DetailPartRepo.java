package osfix.ag.crm.repo.rigging.part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;

@Repository
public interface DetailPartRepo extends JpaRepository<DetailPart, Long> {
}

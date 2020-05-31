package osfix.ag.crm.repo.rigging.part;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;

@Repository
public interface PartsWorkRepo extends JpaRepository<PartsWork, Long> {
    PartsWork findByPartIdAndWorkControlIdAndPartType(Long part_id, Long id, String type);
    PartsWork findByWorkControlIdAndPartType(Long id, String type);
}

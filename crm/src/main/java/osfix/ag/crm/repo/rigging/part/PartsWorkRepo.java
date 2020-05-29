package osfix.ag.crm.repo.rigging.part;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;

@Repository
public interface PartsWorkRepo extends JpaRepository<PartsWork, Long> {
    PartsWork findByPartIdAndAndWorkControlAndPartType(Long part_id, WorkControl workControl, String type);
}

package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.LegalEntity;

import java.util.Set;

@Repository
public interface LegalEntityRepo extends JpaRepository<LegalEntity, Long> {
    Set<LegalEntity> findAllByInnIgnoreCaseContains(String inn);
}

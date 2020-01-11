package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Lepsari;

@Repository
public interface LepsariRepo extends JpaRepository<Lepsari, Long> {
}

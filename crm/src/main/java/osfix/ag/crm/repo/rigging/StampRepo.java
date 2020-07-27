package osfix.ag.crm.repo.rigging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;

import java.util.List;

@Repository
public interface StampRepo extends JpaRepository<Stamp, Long> {
    List<Stamp> findAllByStatus(String status);
}

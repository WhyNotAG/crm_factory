package osfix.ag.crm.repo.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.factory.Packing;

@Repository
public interface PackingRepo extends JpaRepository<Packing, Long> {
}

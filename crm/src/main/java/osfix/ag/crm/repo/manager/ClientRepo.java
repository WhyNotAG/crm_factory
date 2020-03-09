package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.Client;
@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}

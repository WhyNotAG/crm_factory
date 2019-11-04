package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.Client;

public interface ClientRepo extends JpaRepository <Client,Long> {
}

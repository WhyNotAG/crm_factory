package osfix.ag.crm.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Client;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository <Client,Long> {
}

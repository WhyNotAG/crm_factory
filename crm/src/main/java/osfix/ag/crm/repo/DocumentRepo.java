package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.Document;

public interface DocumentRepo extends JpaRepository<Document,Long> {
}

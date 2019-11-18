package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Long> {
}

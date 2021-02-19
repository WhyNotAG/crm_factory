package osfix.ag.crm.repo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.admin.Journal;

@Repository
public interface JournalRepo extends JpaRepository<Journal, Long> {
}

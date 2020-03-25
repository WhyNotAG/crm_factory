package osfix.ag.crm.repo.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.feedback.Discussion;

@Repository
public interface DiscussionRepo extends JpaRepository<Discussion, Long> {
}

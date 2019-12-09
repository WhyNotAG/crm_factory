package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Detail;

@Repository
public interface DetailRepo extends JpaRepository<Detail, Long> {
}
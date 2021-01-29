package osfix.ag.crm.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Log;

@Repository
public interface LogRepo extends JpaRepository<Log, Long> {
    Page<Log> findAll(Pageable pageable);
}

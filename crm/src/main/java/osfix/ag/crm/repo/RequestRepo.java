package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Request;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
    List<Request> findAllByFactory(String factory);
}

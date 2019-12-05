package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.LEMZ;

@Repository
public interface LEMZRepo extends JpaRepository<LEMZ,Long> {

}

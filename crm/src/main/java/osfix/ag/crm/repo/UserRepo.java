package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.User;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

}

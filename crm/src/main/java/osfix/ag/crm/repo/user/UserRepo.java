package osfix.ag.crm.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.user.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
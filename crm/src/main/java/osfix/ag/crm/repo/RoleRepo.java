package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.user.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

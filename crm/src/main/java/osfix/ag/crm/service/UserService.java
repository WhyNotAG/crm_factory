package osfix.ag.crm.service;

import osfix.ag.crm.domain.user.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAll();

    User findByUsername(String username);
    User findById(Long id);
    User save(User user);
    void delete(Long id);
}

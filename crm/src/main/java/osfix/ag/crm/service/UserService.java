package osfix.ag.crm.service;

import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.service.dto.AddUserDTO;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findByUsername(String username);
    User findById(Long id);
    User save(User user);
    void delete(Long id);
    User update(Long id, AddUserDTO user);
}

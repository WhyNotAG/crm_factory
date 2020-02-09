package osfix.ag.crm.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.user.Role;
import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.repo.user.RoleRepo;
import osfix.ag.crm.repo.user.UserRepo;
import osfix.ag.crm.service.UserService;
import osfix.ag.crm.service.dto.AddUserDTO;
import osfix.ag.crm.service.dto.UserDTO;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (user.getRoles().contains(roleRepo.findByName("ROLE_LEMZ")) ||
                user.getRoles().contains(roleRepo.findByName("ROLE_LEPSARI")) ||
                user.getRoles().contains(roleRepo.findByName("ROLE_LIGOVSKIY")))
            { user.getRoles().add(roleRepo.findByName("ROLE_WORKSHOP")); }

        userRepo.save(user);
        return user;
    }

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder,RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() { return userRepo.findAll(); }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
       return userRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(Long id, AddUserDTO user) {
        User userFromDb = userRepo.findById(id).orElse(null);
        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user,userFromDb,"id","created","status","roles", "password");
        userFromDb.setUpdated(new Date());
        return userRepo.save(userFromDb);
    }
}

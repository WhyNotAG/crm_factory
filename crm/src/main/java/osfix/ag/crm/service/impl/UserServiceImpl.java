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

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        userRepo.save(user);
        return user;
    }

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
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
    public User update(Long id, User user) {
        User userFromDb = userRepo.findById(id).orElse(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user,userFromDb,"id","created","status");
        userFromDb.setUpdated(new Date());
        return userRepo.save(userFromDb);
    }
}

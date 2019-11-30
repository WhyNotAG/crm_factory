package osfix.ag.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.user.Role;
import osfix.ag.crm.domain.user.Status;
import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.repo.user.RoleRepo;
import osfix.ag.crm.service.dto.AddUserDTO;
import osfix.ag.crm.service.dto.UserDTO;
import osfix.ag.crm.service.UserService;
import osfix.ag.crm.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    private UserService userService;
    private UserMapper userMapper;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, UserMapper userMapper, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(userMapper.toDtoList(userService.getAll())) ;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable(name = "id")  long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> add(@RequestBody AddUserDTO userDTO) {
        Role role = roleRepo.findByName(userDTO.getRole());
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setRoles(roles);

        User result = userService.save(user);
        return ResponseEntity.ok().body(userMapper.fromEntity(result));
    }


}

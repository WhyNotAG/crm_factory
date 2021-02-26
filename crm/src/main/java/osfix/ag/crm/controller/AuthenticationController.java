package osfix.ag.crm.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.JwtRefreshToken;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.security.jwt.AccessToken;
import osfix.ag.crm.security.jwt.JwtAuthenticationException;
import osfix.ag.crm.security.jwt.JwtTokenProvider;
import osfix.ag.crm.service.JwtRefreshTokenService;
import osfix.ag.crm.service.UserService;
import osfix.ag.crm.service.dto.AuthenticationRequestDTO;
import osfix.ag.crm.service.dto.RefreshTokenDTO;
import osfix.ag.crm.service.dto.SignInResponseDTO;
import osfix.ag.crm.service.mapper.UserMapper;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private JwtRefreshTokenService jwtRefreshTokenService;
    private UserMapper userMapper;
    private LogRepo logRepo;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                    UserService userService, JwtRefreshTokenService jwtRefreshTokenService,
                                    UserMapper userMapper, LogRepo logRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
        this.userMapper = userMapper;
        this.logRepo = logRepo;
    }


    @GetMapping("")
    public String getInfo() {
        return "Добро пожаловать";
    }

    @PostMapping("login")
    public ResponseEntity<SignInResponseDTO> login(@RequestBody AuthenticationRequestDTO requestDto) {
        String username = requestDto.getUsername();
        User loadedUser;
        if (username == null) {
            loadedUser = userService.findByUsername(requestDto.getUsername());
            if (loadedUser == null) {
                throw new UsernameNotFoundException("Error load user");
            }
            username = loadedUser.getUsername();
        }
        try {
            loadedUser = userService.findByUsername(username);
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
                    );


            AccessToken accessToken  = jwtTokenProvider.createToken(username, loadedUser.getRoles());
            JwtRefreshToken refreshToken = createRefreshToken(loadedUser);

            SignInResponseDTO response = new SignInResponseDTO();
            response.setAccessToken(accessToken.getToken());
            response.setExpiredIn(accessToken.getExpiredIn());
            response.setRefreshToken(refreshToken.getToken());
            response.setUser(userMapper.fromEntity(loadedUser));
            loging("Вход", "Вход", "login", loadedUser.getId(), loadedUser.getUsername());
            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            loging("Неудачная попытка входа", "Неудачная попытка входа", "login", null, null);
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    @PostMapping("refreshToken")
    public ResponseEntity<SignInResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDto) {
        return jwtRefreshTokenService.findById(refreshTokenDto.getRefreshToken()).map(jwtRefreshToken -> {
            jwtRefreshTokenService.delete(jwtRefreshToken);

            User user = jwtRefreshToken.getUser();
            log.info("roles", user.getRoles());
            AccessToken accessToken = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            JwtRefreshToken refreshToken = createRefreshToken(user);

            SignInResponseDTO response = new SignInResponseDTO();
            response.setAccessToken(accessToken.getToken());
            response.setExpiredIn(accessToken.getExpiredIn());
            response.setRefreshToken(refreshToken.getToken());
            response.setUser(userMapper.fromEntity(user));

            return ResponseEntity.ok().body(response);
        }).orElseThrow(() -> new JwtAuthenticationException("Invalid refresh token"));

    }

    private JwtRefreshToken createRefreshToken(User user) {

        String refreshToken = jwtTokenProvider.createRefreshToken();
        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();
        jwtRefreshToken.setToken(refreshToken);
        jwtRefreshToken.setUser(user);
        jwtRefreshToken = jwtRefreshTokenService.save(jwtRefreshToken);
        return jwtRefreshToken;
    }

    public void loging(String actionShort, String action, String type, Long id, String username) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        log.setAuthor(username);
        log.setDescription(action);
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}

package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.JwtRefreshToken;

import java.util.List;

public interface JwtRefreshTokenRepo extends JpaRepository<JwtRefreshToken, String> {

    List<JwtRefreshToken> findAllByUserId(Long userId);
}

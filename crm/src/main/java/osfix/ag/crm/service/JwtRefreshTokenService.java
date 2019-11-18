package osfix.ag.crm.service;

import osfix.ag.crm.domain.JwtRefreshToken;

import java.util.Optional;

public interface JwtRefreshTokenService {

    JwtRefreshToken save(JwtRefreshToken token);

    void delete(JwtRefreshToken token);

    Optional<JwtRefreshToken> findById(String id);

}

package osfix.ag.crm.security.jwt;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String explanation) {
        super(explanation);
    }
}

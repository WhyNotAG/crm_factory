package osfix.ag.crm.domain;

import lombok.Data;
import osfix.ag.crm.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "refresh_tokens")
@Data
public class JwtRefreshToken {
    @Id
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

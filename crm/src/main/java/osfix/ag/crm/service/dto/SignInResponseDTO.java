package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.user.Role;

import java.util.List;

@Data
public class SignInResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Long expiredIn;
    private UserDTO user;
}

package osfix.ag.crm.dto;

import lombok.Data;

@Data
public class SignInResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Long expiredIn;
    private UserDto user;
}

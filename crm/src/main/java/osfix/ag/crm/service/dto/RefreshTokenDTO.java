package osfix.ag.crm.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenDTO {
    @NotBlank
    private String refreshToken;
}

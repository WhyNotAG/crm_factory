package osfix.ag.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenDto {
    @NotBlank
    private String refreshToken;
}

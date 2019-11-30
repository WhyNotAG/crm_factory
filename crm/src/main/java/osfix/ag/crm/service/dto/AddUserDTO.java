package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.user.Role;

@Data
public class AddUserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}

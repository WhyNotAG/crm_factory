package osfix.ag.crm.service.dto.manager;

import lombok.Data;
@Data
public class ContactDTO {
    Long id;
    String name;
    String lastName;
    String email;
    String position;
    String phoneNumber;
    Long clientId;
    Boolean sendingMail;
}

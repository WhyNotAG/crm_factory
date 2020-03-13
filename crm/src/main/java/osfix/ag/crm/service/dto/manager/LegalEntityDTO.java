package osfix.ag.crm.service.dto.manager;

import lombok.Data;
@Data
public class LegalEntityDTO {
    Long id;
    String name;
    String inn;
    String kpp;
    String ogrn;
    String bik;
    String checkingAccount;
    String legalAddress;
    String factualAddress;
    Long clientId;
}

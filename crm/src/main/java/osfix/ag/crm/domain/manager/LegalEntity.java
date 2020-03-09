package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "legal")
public class LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "inn")
    String inn;

    @Column(name = "kpp")
    String kpp;

    @Column(name = "ogrn")
    String ogrn;

    @Column(name = "bik")
    String bik;

    @Column(name = "checking_account")
    String checkingAccount;

    @Column(name = "legal_address")
    String legalAddress;

    @Column(name = "factual_address")
    String factualAddress;

    @ManyToOne
    @JsonBackReference
    private Client client;

}

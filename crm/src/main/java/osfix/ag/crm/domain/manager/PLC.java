package osfix.ag.crm.domain.manager;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "plc")
public class PLC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "short_name")
    String shortName;

    @Column(name = "legal_address")
    String legalAddress;

    @Column(name = "mailing_address")
    String mailingAddress;

    @Column(name = "phone")
    String phone;

    @Column(name = "site")
    String site;

    @Column(name = "inn")
    String inn;

    @Column(name = "kpp")
    String kpp;

    @Column(name = "ogrn")
    String ogrn;

    @Column(name = "okpo")
    String okpo;

    @Column(name = "okved")
    String okved;

    @Column(name = "checking account")
    String checkingAccount;

    @Column(name = "bank")
    String bank;

    @Column(name = "сorrespondent_account")
    String correspondentAccount;

    @Column(name = "bik")
    String bik;

    @Column(name = "general_director")
    String generalDirector;

    @Column(name = "accountant")
    String accountant;

    @Column(name = "logo")
    String logo;
}

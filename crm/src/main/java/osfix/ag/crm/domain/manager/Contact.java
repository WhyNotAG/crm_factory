package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "position")
    String position;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "sending_mail")
    Boolean sendingMail;

    @ManyToOne
    @JsonBackReference
    private Client client;
}

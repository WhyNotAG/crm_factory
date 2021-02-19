package osfix.ag.crm.domain.admin;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnoreProperties("journals")
    @JsonIdentityReference
    private Employee employee;

    @Column(name = "comment")
    String comment;

    @Column(name = "date")
    Date date;
}

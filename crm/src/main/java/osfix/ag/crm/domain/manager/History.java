package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    String date;

    @Column(name = "action")
    String action;

    @Column(name = "result")
    String result;

    @Column(name = "comment")
    String comment;

    @ManyToOne
    @JsonBackReference
    private Client client;

}

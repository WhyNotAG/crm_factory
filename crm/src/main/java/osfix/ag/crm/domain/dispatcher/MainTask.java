package osfix.ag.crm.domain.dispatcher;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "main_tasks")
public class MainTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column("date_created")
    Date dateCreated;

    @Column("description")
    String description;

    @Column("responsible")
    String responsible;

    @Column("date_control")
    Date dateControl;

    @Column("status")
    String status;

}

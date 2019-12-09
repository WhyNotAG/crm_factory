package osfix.ag.crm.domain.dispatcher;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "main_tasks")
public class MainTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date_created")
    Date dateCreated;

    @Column(name = "description")
    String description;

    @Column(name = "responsible")
    String responsible;

    @Column(name = "date_control")
    Date dateControl;

    @Column(name = "status")
    String status;

}

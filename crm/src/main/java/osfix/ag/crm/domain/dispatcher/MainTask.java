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
    String dateCreated;

    @Column(name = "description")
    String description;

    @Column(name = "responsible")
    String responsible;

    @Column(name = "date_control")
    String dateControl;

    @Column(name = "status")
    String status;

    @Column(name = "condition")
    String condition;
}

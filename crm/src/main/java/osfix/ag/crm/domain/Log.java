package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs")
@Data
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "author")
    String author;

    @Column(name = "action")
    String action;

    @Column(name = "description")
    String description;

    @Column(name = "type")
    String type;

    @Column(name = "date")
    Date date;
}

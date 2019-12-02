package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    String date;

    @Column(name = "template")
    String template;

    @Column(name = "client")
    String client;

    @Column(name = "price")
    Double price;

    @Column(name = "days_left")
    Integer daysLeft;

    @Column(name = "deadline")
    String deadline;

    @Column(name = "delivery")
    Boolean delivery;

    @Column(name = "status")
    String status;

    @Column(name = "request")
    String request;
}

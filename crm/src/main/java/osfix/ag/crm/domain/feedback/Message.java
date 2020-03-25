package osfix.ag.crm.domain.feedback;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    Date date;

    @Column(name = "author")
    String author;

    @Column(name = "text")
    String text;

    @ManyToOne
    @JsonIdentityReference
    private Discussion discussion;
}

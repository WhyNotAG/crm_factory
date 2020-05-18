package osfix.ag.crm.domain.feedback;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discussion")
@Data
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    Date date;

    @Column(name = "author")
    String author;

    @Column(name = "status")
    String status;

    @Column(name = "subject")
    String subject;

    @Column(name = "text")
    String text;

    @Column(name = "isRead")
    Boolean isRead;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.REFRESH)
    @JsonBackReference
    public List<Message> messages = new ArrayList<>();
}

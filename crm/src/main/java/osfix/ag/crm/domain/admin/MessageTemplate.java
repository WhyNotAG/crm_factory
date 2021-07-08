package osfix.ag.crm.domain.admin;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message_templates")
public class MessageTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "body")
    String body;
}

package osfix.ag.crm.domain.dispatcher.rigging;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Rigging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number")
    String number;

    @Column(name = "name")
    String name;

    @Column(name = "comment")
    String comment;

    @Column(name = "color")
    String color;
}

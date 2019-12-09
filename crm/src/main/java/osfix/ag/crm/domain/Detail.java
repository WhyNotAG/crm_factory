package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number")
    String number;

    @Column(name = "name")
    String name;

    @Column(name = "dimensions")
    String dimensions;

    @Column(name = "processing")
    String processing;
}

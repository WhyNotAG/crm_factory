package osfix.ag.crm.domain.dispatcher.rigging.parts;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number")
    String number;

    @Column(name = "name")
    String name;

    @Column(name = "location")
    String location;

    @Column(name = "comment")
    String comment;

    @Column(name = "cutting_dimensions")
    String cuttingDimensions;

    @Column(name = "milling")
    String milling;

    @Column(name = "harding")
    String harding;

    @Column(name = "grinding")
    String grinding;

    @Column(name = "erosion")
    String erosion;

    @Column(name = "controll")
    String controll;
}

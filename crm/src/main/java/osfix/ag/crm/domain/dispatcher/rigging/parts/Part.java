package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import osfix.ag.crm.domain.product.WorkControlProduct;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "amount")
    String amount;

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

    @Column(name = "color")
    String color;

    @Column(name = "drawing")
    String drawing;

}

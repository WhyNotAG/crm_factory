package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "price_group_products")
public class PriceGroupProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "img_one")
    String imgOne;

    @Column(name = "img_two")
    String imgTwo;

    @Column(name = "img_three")
    String imgThree;

    @Column(name = "img_four")
    String imgFour;

    @Column(name = "img_five")
    String imgFive;

    @Column(name = "img_six")
    String imgSix;
}

package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.LEMZ;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lemz_product")
public class LemzProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("lemzProducts")
    @JsonBackReference
    private LEMZ lemz;
}

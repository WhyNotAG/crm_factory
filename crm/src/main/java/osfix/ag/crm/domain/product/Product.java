package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.Lepsari;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeOfProduct")
    private TypeOfProduct typeOfProduct;

    @Column(name = "photo")
    private String photo;

    @Column(name = "unit")
    private String unit;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "comment")
    private String comment;

    @Column(name = "vendor")
    private String vendor;

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JsonBackReference
    private ProductCategory productCategory;
}

package osfix.ag.crm.domain.product;

import lombok.Data;

import javax.persistence.*;

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
    private Byte photo;

    @Column(name = "unit")
    private String unit;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "packaging")
    private String packaging;
    
    @Column(name = "comment")
    private String comment;



}

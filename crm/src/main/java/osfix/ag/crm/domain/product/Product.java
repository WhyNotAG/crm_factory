package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.user.Role;
import osfix.ag.crm.domain.user.User;

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
    private Byte photo;

    @Column(name = "unit")
    private String unit;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "comment")
    private String comment;

    @JsonIgnoreProperties("products")
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Request> requests;
}

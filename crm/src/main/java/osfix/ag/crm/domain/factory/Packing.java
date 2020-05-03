package osfix.ag.crm.domain.factory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "packing")
public class Packing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "comment")
    String comment;

    @Column(name = "size")
    String size;

    @JsonIgnoreProperties("packings")
    @JsonBackReference
    @ManyToMany(mappedBy = "packings", fetch = FetchType.LAZY)
    private List<Product> products;

}

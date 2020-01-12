package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "category")
    String category;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<Product> products = new ArrayList<>();
}

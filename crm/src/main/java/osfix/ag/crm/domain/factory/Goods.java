package osfix.ag.crm.domain.factory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.product.Product;

import javax.persistence.*;



@Entity
@Data
@Table(name = "Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnoreProperties("goods")
    @JsonManagedReference
    Packing packing;

    @ManyToOne
    @JsonIgnoreProperties("goods")
    @JsonManagedReference
    Product product;

    @Column(name = "barcode")
    String barcode;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "unit")
    String unit;
}

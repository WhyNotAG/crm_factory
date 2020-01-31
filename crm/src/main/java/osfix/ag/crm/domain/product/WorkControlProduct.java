package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.WorkControl;

import javax.persistence.*;

@Entity
@Table(name = "work_control_product")
@Data
public class WorkControlProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JsonBackReference
    WorkControl workControl;
}

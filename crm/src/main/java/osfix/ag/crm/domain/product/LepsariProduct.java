package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.Lepsari;

import javax.persistence.*;

@Entity
@Table(name = "lepsari_product")
@Data
public class LepsariProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne
    @JsonIgnoreProperties("lepsariProducts")
    @JsonBackReference
    private Lepsari lepsari;
}

package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.WorkControlProduct;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "work_control")
@Data
public class WorkControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnoreProperties("workControls")
    @JsonIdentityReference
    WorkList workList;

    @Column(name = "year")
    Integer year;

    @Column(name = "month")
    Integer month;

    @Column(name = "day")
    Integer day;

    @Column(name = "hours")
    Long hours;

    @ManyToOne
    @JsonIgnoreProperties("workControls")
    @JsonIdentityReference
    private Employee employee;

    @JsonIdentityReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "control_product", joinColumns = {@JoinColumn(name = "control_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    @OneToMany(mappedBy = "workControl", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    List<WorkControlProduct> workControlProduct;

}

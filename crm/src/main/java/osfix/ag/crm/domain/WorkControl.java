package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
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
    Double hours;

    @ManyToOne
    @JsonIgnoreProperties("workControls")
    @JsonIdentityReference
    private Employee employee;

    @OneToMany(mappedBy = "workControl", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    List<WorkControlProduct> workControlProduct;

    @OneToMany(mappedBy = "workControl", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    List<WorkControlProduct> workControlTrashBase;

}

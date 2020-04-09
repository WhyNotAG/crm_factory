package osfix.ag.crm.domain.factory;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import osfix.ag.crm.domain.manager.History;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "factory_name")
    String factoryName;

    @Column(name = "assembly")
    String assembly;

    @Column(name = "status")
    String status;

    @Column(name = "date")
    Date date;

    @Column(name = "deliver_by")
    Date deliverBy;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.REFRESH)
    @JsonIdentityReference
    public List<EquipmentProduct> products = new ArrayList<>();
}

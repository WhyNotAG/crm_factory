package osfix.ag.crm.domain.factory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import osfix.ag.crm.domain.manager.Client;

import javax.persistence.*;

@Entity
@Data
@Table(name = "equipment_products")
public class EquipmentProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    String quantity;

    @ManyToOne
    @JsonBackReference
    private Equipment equipment;

}

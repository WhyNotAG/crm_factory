package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import osfix.ag.crm.domain.WorkControl;

import javax.persistence.*;
@Entity
@Table(name = "parts_work")
@Data
public class PartsWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "part_id")
    Long partId;

    @Column(name = "part_type")
    String partType;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne
    @JsonBackReference
    WorkControl workControl;
}

package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "trash_base")
public class TrashBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne
    @JsonIgnoreProperties("trashBase")
    @JsonBackReference
    WorkControl workControl;
}

package osfix.ag.crm.domain;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "storage_lepsari")
@Data
public class StorageLepsari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number")
    Long number;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    String quantity;

    @Column(name = "comment")
    String comment;
}
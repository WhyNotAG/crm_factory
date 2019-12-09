package osfix.ag.crm.domain.dispatcher;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "shipping_registry")
public class ShippingRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    Date date;

    @Column(name = "cargo")
    String cargo;

    @Column(name = "from")
    String from;

    @Column(name = "to")
    String to;

    @Column(name = "driver")
    String driver;
}

package osfix.ag.crm.domain.dispatcher;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "shipping_registry")
public class ShippingRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    String date;

    @Column(name = "cargo")
    String cargo;

    @Column(name = "sender")
    String sender;

    @Column(name = "recipient")
    String recipient;

    @Column(name = "driver")
    String driver;

    @Column(name = "quantity")
    String quantity;
}

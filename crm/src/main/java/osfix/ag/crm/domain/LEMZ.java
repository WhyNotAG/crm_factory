package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lemz")
@Data
public class LEMZ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "shipping_date")
    String shippingDate;

    @Column(name = "date")
    String date;

    @Column(name = "quantity")
    String quantity;

    @Column(name = "code_word") //кодовое слово
    String codeWord;

    @Column(name = "status")
    String status;

    @Column(name = "responsible") //кто отвественный
    String responsible;
}

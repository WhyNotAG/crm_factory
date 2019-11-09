package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "date")
    String date;

    @Column(name = "products")
    String products;

    @Column(name = "quantity")
    String quantity;

    @Column(name = "code_word") //кодовое слово
    String codeWord;

    @Column(name = "status") //кто отвественный
    String status;

    @Column(name = "responsible") //кто отвественный
    String responsible;


}

package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.domain.product.RequestProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "comment")
    String comment;

    @Column(name = "responsible") //кто отвественный
    String responsible;

    @OneToMany(mappedBy = "lemz", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<LemzProduct> lemzProducts = new ArrayList<>();

    @OneToOne
    @JsonBackReference
    Request request;
}

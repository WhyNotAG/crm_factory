package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.user.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "requests")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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

    @JsonIgnoreProperties("requests")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "request_products", joinColumns = {@JoinColumn(name = "request_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;


}

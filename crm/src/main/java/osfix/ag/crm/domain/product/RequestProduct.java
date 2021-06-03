package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.factory.Goods;

import javax.persistence.*;

@Entity
@Data
@Table(name = "request_product")
public class RequestProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("requestProducts")
    @JsonBackReference
    private Request request;

    @ManyToOne
    @JsonManagedReference
    @JsonIgnoreProperties("requestProducts")
    private Goods goods;
}

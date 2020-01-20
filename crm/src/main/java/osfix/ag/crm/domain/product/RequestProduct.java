package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import osfix.ag.crm.domain.Request;

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
}

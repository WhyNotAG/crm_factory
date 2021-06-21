package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.domain.user.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "invoicing_responsible") //кто отвественный
    String invoicingResponsible;

    @Column(name = "comment") //кто отвественный
    String comment;

    @Column(name = "shipping_date")
    String shippingDate;

    @Column(name = "factory")
    String factory;

    @Column(name = "reckoning")
    Double reckoning;

    @Column(name = "sum")
    Double sum;

    @Column(name = "ltd")
    String ltd;

    @Column(name = "inn")
    String inn;

    @Column(name = "paid_status")
    String paidStatus;

    @OneToMany(mappedBy = "request", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<RequestProduct> requestProducts = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("client")
    @JsonManagedReference
    private Client client;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<InvoicingRequest> invoicingRequests = new ArrayList<>();

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<ShippingDocument> shippingDocuments = new ArrayList<>();
}

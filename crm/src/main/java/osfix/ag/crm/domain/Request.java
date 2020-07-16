package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
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

    @OneToMany(mappedBy = "request", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<RequestProduct> requestProducts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "request")
    @JsonManagedReference
    @JsonIgnore
    public LEMZ lemz;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "request")
    @JsonManagedReference
    @JsonIgnore
    public Lepsari lepsari;
}

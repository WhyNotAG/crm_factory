package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.product.RequestProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;

    @Column(name = "site")
    String site;

    @Column(name = "comment")
    String comment;

    @Column(name = "storage_address")
    String storageAddress;

    @Column(name = "work_condition")
    String workCondition;

    @Column(name = "price")
    String price;

    @Column(name = "discount")
    String discount;

    @Column(name = "checks")
    String check;

    @Column(name = "client_type")
    String clientType;

    @Column(name = "manager")
    String manager;

    @Column(name = "next_date_contact")
    Date nextDateContact;

    @Column(name = "favorite")
    Boolean favorite;

    @Column(name = "type")
    String type;

    @Column(name = "city")
    String city;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REFRESH)
    @JsonIdentityReference
    public List<LegalEntity> legalEntities = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REFRESH)
    @JsonIdentityReference
    public List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REFRESH)
    @JsonIdentityReference
    public List<History> histories = new ArrayList<>();

    @ManyToOne
    @JsonIdentityReference
    private Category category;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REFRESH)
    @JsonBackReference
    public List<Request> requests = new ArrayList<>();

}

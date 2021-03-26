package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "price_lists")
@Data
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "uri")
    String uri;

    @OneToMany(mappedBy = "prices", cascade = CascadeType.REFRESH)
    @JsonBackReference
    public List<Client> clients = new ArrayList<>();
}

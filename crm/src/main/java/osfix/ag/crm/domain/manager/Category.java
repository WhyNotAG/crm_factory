package osfix.ag.crm.domain.manager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "client_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "visibility")
    String visibility;

    @Column(name = "type")
    String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    @JsonBackReference
    public List<Client> clients = new ArrayList<>();
}

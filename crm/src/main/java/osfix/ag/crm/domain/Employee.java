package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import osfix.ag.crm.domain.product.RequestProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "year_of_Birth")
    String yearOfBirth;

    @Column(name = "citizenship")
    String citizenship;

    @Column(name = "workshop")
    String workshop;

    @Column(name = "position")
    String position;

    @Column(name = "passportScan1")
    String passportScan1;

    @Column(name = "passportScan2")
    String passportScan2;

    @Column(name = "patent")
    String patent;

    @Column(name = "comment")
    String comment;

    @Column(name = "relevance")
    String relevance;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "patent_expiration_date")
    Date patentExpirationDate;

    @Column(name = "registration_expiration_date")
    Date registrationExpirationDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonBackReference
    public List<WorkControl> workControls = new ArrayList<>();

}

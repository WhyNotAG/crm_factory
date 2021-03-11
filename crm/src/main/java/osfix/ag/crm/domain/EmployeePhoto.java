package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee_photos")
public class EmployeePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "url")
    String url;

    @ManyToOne
    @JsonIgnoreProperties("employeePhotos")
    @JsonIdentityReference
    private Employee employee;
}

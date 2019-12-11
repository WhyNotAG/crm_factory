package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;

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

}

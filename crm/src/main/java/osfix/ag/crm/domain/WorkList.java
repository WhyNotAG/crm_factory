package osfix.ag.crm.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "work_list")
@Data
public class WorkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "work")
    String work;
}

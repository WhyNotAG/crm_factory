package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work_list")
@Data
public class WorkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "work")
    String work;

    @OneToMany(mappedBy = "workList", cascade = CascadeType.REFRESH)
    @JsonBackReference
    public List<WorkControl> workControls = new ArrayList<>();
}

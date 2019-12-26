package osfix.ag.crm.domain.dispatcher.rigging;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "benches")
public class Bench extends Rigging {
    @Getter
    @Setter
    @OneToMany(mappedBy = "bench", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<BenchPart> benchParts = new ArrayList<>();
}
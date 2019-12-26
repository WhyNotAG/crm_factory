package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.Bench;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bench_parts")
public class BenchPart extends Part {
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties("benchParts")
    @JsonBackReference
    private Bench bench;
}

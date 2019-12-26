package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.Press;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "press_parts")
public class PressPart extends Part {
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties("pressParts")
    @JsonBackReference
    private Press press;
}

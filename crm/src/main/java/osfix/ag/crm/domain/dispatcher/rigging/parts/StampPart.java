package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stamp_parts")
public class StampPart extends Part {
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties("stampParts")
    @JsonBackReference
    private Stamp stamp;

}

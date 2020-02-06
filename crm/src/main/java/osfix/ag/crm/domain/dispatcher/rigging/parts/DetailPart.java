package osfix.ag.crm.domain.dispatcher.rigging.parts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.Detail;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detail_parts")
public class DetailPart extends Part {
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties("detailParts")
    @JsonBackReference
    private Detail detail;
}

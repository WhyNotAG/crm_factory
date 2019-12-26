package osfix.ag.crm.domain.dispatcher.rigging;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stamps")
public class Stamp extends Rigging{
    @Getter
    @Setter
    @OneToMany(mappedBy = "stamp", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<StampPart> stampParts = new ArrayList<>();

}

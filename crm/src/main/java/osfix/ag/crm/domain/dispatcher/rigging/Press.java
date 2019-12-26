package osfix.ag.crm.domain.dispatcher.rigging;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "press")
public class Press extends Rigging {
    @Getter
    @Setter
    @OneToMany(mappedBy = "press", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<PressPart> pressParts = new ArrayList<>();
}

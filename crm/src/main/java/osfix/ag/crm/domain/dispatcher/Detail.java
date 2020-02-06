package osfix.ag.crm.domain.dispatcher;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import osfix.ag.crm.domain.dispatcher.rigging.Rigging;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "details")
public class Detail extends Rigging {
    @Getter
    @Setter
    @OneToMany(mappedBy = "detail", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<DetailPart> detailParts = new ArrayList<>();
}

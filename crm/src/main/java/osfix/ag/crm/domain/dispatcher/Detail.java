package osfix.ag.crm.domain.dispatcher;

import lombok.Data;
import osfix.ag.crm.domain.dispatcher.rigging.Rigging;

import javax.persistence.*;

@Entity
@Data
@Table(name = "details")
public class Detail extends Rigging {
}

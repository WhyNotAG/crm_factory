package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class PartsWorkDTO {
    Long id;
    Long partId;
    String partType;
    Long quantity;
    Long workControl;
    String name;
}

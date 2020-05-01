package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class PackingDTO {
    Long id;
    String name;
    Integer quantity;
    String comment;
    String size;
}

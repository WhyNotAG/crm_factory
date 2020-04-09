package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class EquipmentProductDTO {
    Long id;
    String name;
    String quantity;
    Long equipmentId;
}

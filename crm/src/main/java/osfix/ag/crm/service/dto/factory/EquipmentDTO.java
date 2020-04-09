package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class EquipmentDTO {
    Long id;
    String name;
    String factoryName;
    String assembly;
    String status;
    Long date;
    Long deliverBy;
}

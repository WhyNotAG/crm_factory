package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class WorkControlProductDTO {
    Long id;
    Long workControlId;
    Long productId;
    Long quantity;
}

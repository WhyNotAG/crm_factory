package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class GoodsDTO {
    Long id;
    Long packingId;
    Long productId;
    String barcode;
    Integer quantity;
    String unit;
}

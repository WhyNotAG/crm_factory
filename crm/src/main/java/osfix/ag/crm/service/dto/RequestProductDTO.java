package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.factory.Goods;

@Data
public class RequestProductDTO {
    private Long id;
    private String name;
    private String packaging;
    private String quantity;
    private Long requestId;
    private String status;
    private Long goodsId;
    private Long productId;
}

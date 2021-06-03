package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.service.dto.factory.GoodsViewDTO;

@Data
public class RequestProductViewDTO {
    private Long id;
    private String name;
    private String packaging;
    private String quantity;
    private Long requestId;
    private String status;
    private GoodsViewDTO goods;
}

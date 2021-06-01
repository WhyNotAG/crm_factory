package osfix.ag.crm.service.dto.factory;

import lombok.Data;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.service.dto.ProductsWithoutPhotoDTO;

@Data
public class GoodsViewDTO {
    Long id;
    Packing packing;
    ProductsWithoutPhotoDTO product;
    String barcode;
    Integer quantity;
    String unit;
}

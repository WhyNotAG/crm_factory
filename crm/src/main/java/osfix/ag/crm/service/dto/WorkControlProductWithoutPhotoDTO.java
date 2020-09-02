package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.WorkControl;
@Data
public class WorkControlProductWithoutPhotoDTO {
    Long id;
    private ProductsWithoutPhotoDTO product;
    Long quantity;
    WorkControl workControl;
}

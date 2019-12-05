package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.product.TypeOfProduct;
@Data
public class ProductsDTO {
    private long id;
    private String name;
    private TypeOfProduct typeOfProduct;
    private String photo;
    private String unit;
    private Float weight;
    private String packaging;
    private String comment;
    private String vendor;
}

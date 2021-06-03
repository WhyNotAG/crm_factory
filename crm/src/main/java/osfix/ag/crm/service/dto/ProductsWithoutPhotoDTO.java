package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.product.TypeOfProduct;

@Data
public class ProductsWithoutPhotoDTO {
    private long id;
    private String name;
    private TypeOfProduct typeOfProduct;
    private String unit;
    private Float weight;
    //private String packaging;
    private String comment;
    private String vendor;
    private String category;
    private String description;
    private String barcode;
    private String productionLocation;
}

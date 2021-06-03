package osfix.ag.crm.service.dto;

import lombok.Data;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.domain.product.TypeOfProduct;

import java.util.List;
@Data
public class ProductsWithPackingsDTO {
    private long id;
    private String name;
    private TypeOfProduct typeOfProduct;
    private String photo;
    private String unit;
    private Float weight;
    //private String packaging;
    private String comment;
    private String vendor;
    private String category;
    private String productionLocation;
    private String description;
    private String barcode;
    //private List<Packing> packings;
}

package osfix.ag.crm.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddProductsDTO {
    List<String> productsName;
    List<String> quantity;
    List<String> packaging;
}

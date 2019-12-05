package osfix.ag.crm.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddProductsDTO {
    List<Long> productsId;
    List<String> quantity;
    List<String> packing;
}

package osfix.ag.crm.service.dto.request;

import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import osfix.ag.crm.domain.RequestProduct;
import osfix.ag.crm.domain.product.Product;

import javax.persistence.Column;
import java.util.List;

@Data
public class RequestDTO {
    Long id;
    String date;
    String quantity;
    String codeWord;
    String status;
    String responsible;
    List<Long> productsId;
//    List<String> weight;
}

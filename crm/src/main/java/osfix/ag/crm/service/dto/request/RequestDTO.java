package osfix.ag.crm.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestDTO {
    Long id;
    String date;
    String quantity;
    String codeWord;
    String status;
    String responsible;
    String factory;
    String shippingDate;
    String comment;
    List<Long> productsId;
//    List<String> weight;
}

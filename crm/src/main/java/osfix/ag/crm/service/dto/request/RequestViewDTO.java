package osfix.ag.crm.service.dto.request;

import lombok.Data;
import osfix.ag.crm.service.dto.RequestProductViewDTO;

import java.util.List;

@Data
public class RequestViewDTO {
    Long id;
    String date;
    String quantity;
    String codeWord;
    String status;
    String responsible;
    String factory;
    String shippingDate;
    String comment;
    Double sum;
    Double reckoning;
    List<RequestProductViewDTO> requestProducts;
}

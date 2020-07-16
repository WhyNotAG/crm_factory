package osfix.ag.crm.service.dto.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class FactoryDTO {
    Long id;
    String shippingDate;
    String date;
    String quantity;
    String codeWord;
    String status;
    String comment;
    String responsible;
}

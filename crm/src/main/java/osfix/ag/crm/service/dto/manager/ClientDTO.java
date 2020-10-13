package osfix.ag.crm.service.dto.manager;

import lombok.Data;
@Data
public class ClientDTO {
    Long id;
    String name;
    String site;
    String comment;
    String storageAddress;
    String workCondition;
    String price;
    String discount;
    String check;
    String clientType;
    String manager;
    Long nextDateContact;
    String categoryName;
    Long categoryId;
    Boolean favorite;
    String type;
    String city;
}

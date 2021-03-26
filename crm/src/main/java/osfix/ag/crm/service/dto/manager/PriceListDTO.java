package osfix.ag.crm.service.dto.manager;

import lombok.Data;

import java.util.List;

@Data
public class PriceListDTO {
    Long id;
    String uri;
    List<Long> clientId;
}

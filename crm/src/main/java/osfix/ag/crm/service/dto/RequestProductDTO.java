package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class RequestProductDTO {
    private Long id;
    private String name;
    private String packaging;
    private String quantity;
    private Long requestId;
}

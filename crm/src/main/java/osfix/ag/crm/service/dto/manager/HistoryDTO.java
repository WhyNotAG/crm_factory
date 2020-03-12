package osfix.ag.crm.service.dto.manager;

import lombok.Data;

@Data
public class HistoryDTO {
    Long id;
    String date;
    String action;
    String result;
    String comment;
    Long clientId;
}

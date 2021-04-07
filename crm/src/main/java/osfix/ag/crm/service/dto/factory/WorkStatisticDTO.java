package osfix.ag.crm.service.dto.factory;

import lombok.Data;

@Data
public class WorkStatisticDTO {
    String productName;
    Long value; //общее кол-во
    Double average; //среднее
    Double hoursSum; //кол-во записей
}

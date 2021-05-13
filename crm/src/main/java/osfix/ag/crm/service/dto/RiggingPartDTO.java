package osfix.ag.crm.service.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class RiggingPartDTO {
    Long id;
    String number;
    String name;
    String location;
    String comment;
    String amount;
    String cutting;
    String dimensions;
    String milling;
    String harding;
    String grinding;
    String erosion;
    String control;
    Long riggingId;
    String color;
    String drawing;
}

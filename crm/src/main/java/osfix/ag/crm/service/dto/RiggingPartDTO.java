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
    String cuttingDimensions;
    String milling;
    String harding;
    String grinding;
    String erosion;
    String controll;
    Long riggingId;
}

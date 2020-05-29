package osfix.ag.crm.service.dto.factory;

import lombok.Data;

import javax.persistence.Column;
@Data
public class PartsDTO {
    Long id;
    String number;
    String name;
    String location;
    String comment;
    String amount;
    String cuttingDimensions;
    String milling;
    String harding;
    String grinding;
    String erosion;
    String controll;
    String color;
    String drawing;
    String partsType;
    Long quantity;
}

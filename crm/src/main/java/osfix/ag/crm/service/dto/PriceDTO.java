package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class PriceDTO {
    private double retail;
    private double less1500;
    private double less5000;
    private double partner;
    private double dialler;
    private double distributor;
    private double stopPrice;
    private double stopPriceAll;
    private double oldPrice;
}

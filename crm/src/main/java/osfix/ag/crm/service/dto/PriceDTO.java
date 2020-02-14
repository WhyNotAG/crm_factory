package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class PriceDTO {
    private double retailPrice;
    private double lessThan1500Price;
    private double lessThan5000Price;
    private double partnerPrice;
    private double dealerPrice;
    private double distributorPrice;
    private double units;
    private double cost;
    private double retailMarketPrice;
    private double stopPrice;
    private double stopPriceAll;
}

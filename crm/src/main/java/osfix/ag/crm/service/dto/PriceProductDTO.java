package osfix.ag.crm.service.dto;

import lombok.Data;

@Data
public class PriceProductDTO {
    Long id;
    String name;
    String number;
    String description;
    String units;
    String cost;
    String retailMarketPrice;
    String retailPrice;
    String partnerPrice;
    String dealerPrice;
    String distributorPrice;
    String lessThan1500Price;
    String lessThan5000Price;
    Long priceGroupProductId;
}

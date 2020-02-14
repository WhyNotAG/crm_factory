package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "price_product")
public class PriceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "number")
    String number;

    @Column(name = "description")
    String description;

    @Column(name = "units")
    String units;

    @Column(name = "cost")
    String cost;

    @Column(name = "retail_market_price")
    String retailMarketPrice;

    @Column(name = "retail_price")
    String retailPrice;

    @Column(name = "partner_price")
    String partnerP;

    @Column(name = "dealer_price")
    String dealerPrice;

    @Column(name = "distributor_price")
    String distributorPrice;

    @Column(name = "less_than_1500_price")
    String lessThan1500Price;

    @Column(name = "less_than_5000_price")
    String lessThan5000Price;

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JsonBackReference
    private PriceGroupProduct priceGroupProduct;


}

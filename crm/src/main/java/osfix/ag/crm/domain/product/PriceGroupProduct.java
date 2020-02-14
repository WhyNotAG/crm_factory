package osfix.ag.crm.domain.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "price_group_products")
public class PriceGroupProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "img")
    String img;

    @Column(name = "description")
    String description;

    @Column(name = "location_type")
    String locationType;

    @Column(name = "link_address")
    String linkAddress;

    @Column(name = "info_text")
    String infoText;

    @Column(name = "category")
    String category;

    @Column(name = "cost")
    String cost;

    @Column(name = "retail_market_price")
    String retailMarketPrice;

    @Column(name = "partner_price")
    String partnerPrice;

    @Column(name = "dealer_price")
    String dealerPrice;

    @Column(name = "distributor_price")
    String distributorPrice;

    @OneToMany(mappedBy = "priceGroupProduct", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    public List<PriceProduct> products = new ArrayList<>();
}

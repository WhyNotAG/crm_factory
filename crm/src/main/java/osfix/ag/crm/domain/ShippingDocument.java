package osfix.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shipping_documents")
@Data
public class ShippingDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "url")
    String url;

    @ManyToOne
    @JsonIgnoreProperties("shippingDocuments")
    @JsonBackReference
    private Request request;
}

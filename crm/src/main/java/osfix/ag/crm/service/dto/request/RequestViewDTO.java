package osfix.ag.crm.service.dto.request;

import lombok.Data;
import osfix.ag.crm.domain.InvoicingRequest;
import osfix.ag.crm.domain.ShippingDocument;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.service.dto.RequestProductViewDTO;

import java.util.List;

@Data
public class RequestViewDTO {
    Long id;
    String date;
    String quantity;
    String codeWord;
    String status;
    String responsible;
    String factory;
    String shippingDate;
    String comment;
    Double sum;
    Double reckoning;
    Client client;
    String ltd;
    String inn;
    String paid;
    String invoicingResponsible;
    List<RequestProductViewDTO> requestProducts;
    List<InvoicingRequest> invoicingRequest;
    List<ShippingDocument> shippingDocuments;
}

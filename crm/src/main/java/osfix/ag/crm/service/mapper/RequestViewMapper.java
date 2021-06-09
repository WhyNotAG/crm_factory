package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.repo.InvoicingRequestRepo;
import osfix.ag.crm.service.dto.request.RequestDTO;
import osfix.ag.crm.service.dto.request.RequestViewDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestViewMapper implements EntityMapper<Request, RequestViewDTO> {
    @Autowired
    private RequestProductViewMapper requestProductViewMapper;

    @Autowired
    InvoicingRequestRepo invoicingRequestRepo;

    @Override
    public Request toEntity(RequestViewDTO dto) {
        Request request = new Request();
        request.setResponsible(dto.getResponsible());
        request.setQuantity(dto.getQuantity());
        request.setId(dto.getId());
        request.setDate(dto.getDate());
        request.setCodeWord(dto.getCodeWord());
        request.setStatus(dto.getStatus());
        request.setFactory(dto.getFactory());
        request.setShippingDate(dto.getShippingDate());
        request.setComment(dto.getComment());
        request.setSum(dto.getSum());
        request.setReckoning(dto.getReckoning());
        request.setClient(dto.getClient());
        request.setLtd(dto.getLtd());
        request.setInn(dto.getInn());
        request.setRequestProducts(requestProductViewMapper.fromDtoList(dto.getRequestProducts()));
        request.setInvoicingRequests(dto.getInvoicingRequest());
        request.setPaid(dto.getPaid());
        return request;
    }

    @Override
    public RequestViewDTO fromEntity(Request entity) {
        RequestViewDTO dto = new RequestViewDTO();
        dto.setResponsible(entity.getResponsible());
        dto.setQuantity(entity.getQuantity());
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setCodeWord(entity.getCodeWord());
        dto.setStatus(entity.getStatus());
        dto.setShippingDate(entity.getShippingDate());
        dto.setComment(entity.getComment());
        dto.setFactory(entity.getFactory());
        dto.setReckoning(entity.getReckoning());
        dto.setSum(entity.getSum());
        dto.setClient(entity.getClient());
        dto.setLtd(entity.getLtd());
        dto.setInn(entity.getInn());
        dto.setRequestProducts(requestProductViewMapper.toDtoList(entity.getRequestProducts()));
        dto.setInvoicingRequest(entity.invoicingRequests);
        dto.setPaid(entity.getPaid());
        return dto;
    }

    @Override
    public List<Request> fromDtoList(List<RequestViewDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestViewDTO> toDtoList(List<Request> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

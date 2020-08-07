package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.dto.request.RequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapper implements EntityMapper<Request, RequestDTO> {
    @Override
    public Request toEntity(RequestDTO dto) {
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
        //request.setRequestProducts(dto.getRequestProducts());
        return request;
    }

    @Override
    public RequestDTO fromEntity(Request entity) {
        RequestDTO dto = new RequestDTO();
        dto.setResponsible(entity.getResponsible());
        dto.setQuantity(entity.getQuantity());
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setCodeWord(entity.getCodeWord());
        dto.setStatus(entity.getStatus());
        dto.setShippingDate(entity.getShippingDate());
        dto.setComment(entity.getComment());
        dto.setFactory(dto.getFactory());
        dto.setReckoning(dto.getReckoning());
        dto.setSum(dto.getSum());
        //dto.setRequestProducts(entity.getRequestProducts());
        return dto;
    }

    @Override
    public List<Request> fromDtoList(List<RequestDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> toDtoList(List<Request> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

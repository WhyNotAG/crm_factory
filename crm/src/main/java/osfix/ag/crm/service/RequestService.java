package osfix.ag.crm.service;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.dto.request.RequestViewDTO;

import java.util.List;

@Service
public interface RequestService {
    List<RequestViewDTO> findAll();
    List<RequestViewDTO> findByFactory(String factory);
    RequestViewDTO findById(Long id);
    RequestViewDTO update(Long id, Request request);
    RequestViewDTO save(Request request);
    void delete(Long id);
    void changeStatus(Long id, String status);
    RequestViewDTO addProduct(Long id, List<String> products_id, List<String> quantity, List<String> packing);
    void deletePro(Long id);
    RequestViewDTO copy(Long id, String factory);
    RequestViewDTO addClient(Long requestId, Long clientId);
}

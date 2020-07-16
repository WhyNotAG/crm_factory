package osfix.ag.crm.service;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Request;

import java.util.List;

@Service
public interface RequestService {
    List<Request> findAll();
    Request findById(Long id);
    Request update(Long id, Request request);
    Request save(Request request);
    void delete(Long id);
    void changeStatus(Long id, String status);
    Request addProduct(Long id, List<String> products_id, List<String> quantity, List<String> packing);
    void deletePro(Long id);
    void copy(Long id, String factory);
}

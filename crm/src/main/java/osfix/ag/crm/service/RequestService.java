package osfix.ag.crm.service;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.domain.Request;

import java.util.List;

@Service
public interface RequestService {
    List<Request> findAll();
    Request findById(Long id);
    Request update(Long id, Request request);
    Request save(Request request);
    void delete(Long id);
}

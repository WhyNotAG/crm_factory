package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.service.RequestService;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public List<Request> findAll() { return requestRepo.findAll(); }

    @Override
    public Request findById(Long id) { return findId(id); }

    @Override
    public Request update(Long id, Request request) {
        Request requestFromDb = findId(id);
        BeanUtils.copyProperties(request,requestFromDb, "id");
        return requestRepo.save(request);
    }

    @Override
    public Request save(Request request) {
        requestRepo.save(request);
        return request;
    }

    @Override
    public void delete(Long id) { requestRepo.deleteById(id); }

    public Request findId(Long id) {
        Request request = requestRepo.findById(id).orElse(null);
        if (request == null) {
            return null;
        } else {
            return request;
        }
    }
}

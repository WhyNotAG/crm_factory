package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.RequestService;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;
    private ClientRepo clientRepo;

    public RequestServiceImpl(RequestRepo requestRepo, ClientRepo clientRepo) {
        this.requestRepo = requestRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Request> findAll() { return requestRepo.findAll(); }

    @Override
    public Request findById(Long id) { return findId(id); }

    @Override
    public Request update(Long id, Request request) {
        Request requestFromDb = findId(id);
        BeanUtils.copyProperties(request,requestFromDb, "id", "client");
        return requestRepo.save(requestFromDb);
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

    @Override
    public void changeStatus(Long id, String status) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setStatus(status);

        requestRepo.save(request);
    }

    @Override
    public Request addProduct(Long id, List<String> prName, List<String> quantity, List<String> packaging) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setRequestProducts(null);
        RequestProduct requestProduct = new RequestProduct();

        for (int i = 0; i < prName.size(); i++) {
            requestProduct.setPackaging(packaging.get(i));
            requestProduct.setName(prName.get(i));
            requestProduct.setQuantity(quantity.get(i));
            request.getRequestProducts().add(requestProduct);
        }

        return requestRepo.save(request);
    }

    @Override
    public Request copy(Long id, String factory) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setFactory(factory);
        return requestRepo.save(request);
    }

    @Override
    public Request addClient(Long requestId, Long clientId) {
        Client client = clientRepo.findById(clientId).orElse(null);
        Request request = requestRepo.findById(requestId).orElse(null);
        request.setClient(client);
        return requestRepo.save(request);
    }

    @Override
    public List<Request> findByFactory(String factory) {
        return requestRepo.findAllByFactory(factory);
    }

    public void deletePro(Long id) {
    }
}

package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.RequestProduct;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.ProductRepo;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.mapper.RequestMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;
    private ProductRepo productRepo;

    public RequestServiceImpl(RequestRepo requestRepo, ProductRepo productRepo) {
        this.requestRepo = requestRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<Request> findAll() { return requestRepo.findAll(); }

    @Override
    public Request findById(Long id) { return findId(id); }

    @Override
    public Request update(Long id, Request request) {
        Request requestFromDb = findId(id);
        BeanUtils.copyProperties(request,requestFromDb, "id");
        Request result = requestRepo.save(requestFromDb);
        return result;
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
    public Request addProduct(Long id, List<Long> products_id, List<String> quantity, List<String> packing) {
        Request request = requestRepo.findById(id).orElse(null);
        RequestProduct requestProduct = new RequestProduct();
        List<RequestProduct> requestProducts = new ArrayList<>();
        int length = 0;
        for (Long pI : products_id) {
            requestProduct.setProduct(productRepo.findById(pI).orElse(null));
            requestProduct.setQuantity(quantity.get(length));
            requestProduct.setPacking(packing.get(length));
            requestProducts.add(requestProduct);
            length++;
        }

        requestProduct.setRequest(request);
        request.setRequestProducts(requestProducts);
        requestRepo.save(request);
        return request;
    }
}

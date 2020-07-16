package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.LEMZRepo;
import osfix.ag.crm.repo.LepsariRepo;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.LemzProductService;
import osfix.ag.crm.service.LepsariProductService;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;
    private LepsariRepo lepsariRepo;
    private LEMZRepo lemzRepo;
    private LemzProductService lemzProductService;
    private LepsariProductService lepsariProductService;

    public RequestServiceImpl(RequestRepo requestRepo, LEMZRepo lemzRepo, LepsariRepo lepsariRepo,
                              LemzProductService lemzProductService, LepsariProductService lepsariProductService) {
        this.requestRepo = requestRepo;
        this.lemzRepo = lemzRepo;
        this.lepsariRepo = lepsariRepo;
        this.lemzProductService = lemzProductService;
        this.lepsariProductService = lepsariProductService;
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

        if(request.getLemz() != null) {
            request.getLemz().setStatus(status);
            lemzRepo.save(request.getLemz());
        }


        if(request.getLepsari() != null) {
            request.getLepsari().setStatus(status);
            lepsariRepo.save(request.getLepsari());
        }
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
    public void copy(Long id, String factory) {
        Request request = requestRepo.findById(id).orElse(null);

        if(factory.equals("Lemz")){
            List<LemzProduct> lemzProducts = new ArrayList<>();
            LEMZ lemz = new LEMZ();
            lemz.setStatus(request.getStatus());
            lemz.setCodeWord(request.getCodeWord());
            lemz.setComment("");
            lemz.setDate(request.getDate());
            lemz.setQuantity(request.getQuantity());
            lemz.setResponsible(request.getResponsible());


            lemz.setShippingDate(request.getDate());
            lemz.setLemzProducts(lemzProducts);
            lemz.setRequest(request);
            lemz  = lemzRepo.save(lemz);

            for(RequestProduct product : request.getRequestProducts()) {
                RequestProductDTO requestProductDTO = new RequestProductDTO();
                requestProductDTO.setName(product.getName());
                requestProductDTO.setPackaging(product.getPackaging());
                requestProductDTO.setQuantity(product.getQuantity());
                requestProductDTO.setRequestId(lemz.getId());
                requestProductDTO.setStatus(product.getStatus());
                lemzProductService.save(requestProductDTO);
            }
            request.setLemz(lemz);
        }

        if(factory.equals("Lepsari")){
            List<LepsariProduct> lepsariProducts = new ArrayList<>();
            Lepsari lepsari = new Lepsari();
            lepsari.setStatus(request.getStatus());
            lepsari.setCodeWord(request.getCodeWord());
            lepsari.setComment("");
            lepsari.setDate(request.getDate());
            lepsari.setQuantity(request.getQuantity());
            lepsari.setResponsible(request.getResponsible());


            lepsari.setShippingDate(request.getDate());
            lepsari.setLepsariProducts(lepsariProducts);
            lepsari.setRequest(request);
            lepsari = lepsariRepo.save(lepsari);

            for(RequestProduct product : request.getRequestProducts()) {
                RequestProductDTO requestProductDTO = new RequestProductDTO();
                requestProductDTO.setName(product.getName());
                requestProductDTO.setPackaging(product.getPackaging());
                requestProductDTO.setQuantity(product.getQuantity());
                requestProductDTO.setRequestId(lepsari.getId());
                requestProductDTO.setStatus(product.getStatus());
                lepsariProductService.save(requestProductDTO);
            }

            request.setLepsari(lepsari);
        }
    }

    public void deletePro(Long id) {
    }
}

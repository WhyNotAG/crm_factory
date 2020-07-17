package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.repo.LEMZRepo;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.RequestProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.beans.beancontext.BeanContext;
import java.util.List;

@Service
public class LEMZServiceImpl implements LEMZService {
    private LEMZRepo lemzRepo;
    private RequestRepo requestRepo;
    private RequestProductService requestProductService;
    public LEMZServiceImpl(LEMZRepo lemzRepo, RequestRepo requestRepo,
                           RequestProductService requestProductService) {
        this.lemzRepo = lemzRepo;
        this.requestRepo = requestRepo;
        this.requestProductService = requestProductService;
    }

    @Override
    public LEMZ findById(Long id) {
        return lemzRepo.findById(id).orElse(null);
    }

    @Override
    public LEMZ save(LEMZ lemz) {
        return lemzRepo.save(lemz);
    }

    @Override
    public void delete(Long id) {
        lemzRepo.deleteById(id);
    }

    @Override
    public LEMZ update(Long id, LEMZ lemz) {
        LEMZ lemzFromDb = lemzRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(lemz,lemzFromDb,"id");
        if(lemzFromDb.getRequest() != null) {

            lemzFromDb.getRequest().setStatus(lemzFromDb.getStatus());
            lemzFromDb.getRequest().setCodeWord(lemzFromDb.getCodeWord());
            lemzFromDb.getRequest().setDate(lemzFromDb.getDate());
            lemzFromDb.getRequest().setQuantity(lemzFromDb.getQuantity());
            lemzFromDb.getRequest().setResponsible(lemzFromDb.getResponsible());


            lemzFromDb.getRequest().setDate(lemzFromDb.getDate());

            for(LemzProduct product : lemzFromDb.getLemzProducts()) {
                RequestProductDTO requestProductDTO = new RequestProductDTO();
                requestProductDTO.setName(product.getName());
                requestProductDTO.setPackaging(product.getPackaging());
                requestProductDTO.setQuantity(product.getQuantity());
                requestProductDTO.setRequestId(lemzFromDb.getRequest().getId());
                requestProductDTO.setStatus(product.getStatus());
                requestProductService.save(requestProductDTO);
            }
            requestRepo.save(lemzFromDb.getRequest());
        }
        return lemzRepo.save(lemzFromDb);
    }

    @Override
    public void changeStatus(Long id, String status) {
        LEMZ lemz = lemzRepo.findById(id).orElse(null);
        lemz.setStatus(status);
        if(lemz.getRequest() != null) {
            lemz.getRequest().setStatus(status);
            requestRepo.save(lemz.getRequest());
        }
        lemzRepo.save(lemz);
    }
    @Override
    public List<LEMZ> findAll() {
        return lemzRepo.findAll();
    }
}

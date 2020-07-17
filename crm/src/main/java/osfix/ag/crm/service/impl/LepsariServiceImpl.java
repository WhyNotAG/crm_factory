package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.LepsariRepo;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.service.LepsariService;
import osfix.ag.crm.service.RequestProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

@Service
public class LepsariServiceImpl implements LepsariService {
    private LepsariRepo lepsariRepo;
    private RequestRepo requestRepo;
    private RequestProductService requestProductService;

    public LepsariServiceImpl(LepsariRepo lepsariRepo, RequestRepo requestRepo,
                              RequestProductService requestProductService ) {
        this.lepsariRepo = lepsariRepo;
        this.requestRepo = requestRepo;
        this.requestProductService = requestProductService;
    }

    @Override
    public Lepsari findById(Long id) { return lepsariRepo.findById(id).orElse(null); }

    @Override
    public Lepsari save(Lepsari lepsari) { return lepsariRepo.save(lepsari); }

    @Override
    public void delete(Long id) { lepsariRepo.deleteById(id); }

    @Override
    public Lepsari update(Long id, Lepsari lepsari) {
        Lepsari lepsariFromDb = lepsariRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(lepsari,lepsariFromDb,"id");
        if(lepsariFromDb.getRequest() != null) {

            lepsariFromDb.getRequest().setStatus(lepsariFromDb.getStatus());
            lepsariFromDb.getRequest().setCodeWord(lepsariFromDb.getCodeWord());
            lepsariFromDb.getRequest().setDate(lepsariFromDb.getDate());
            lepsariFromDb.getRequest().setQuantity(lepsariFromDb.getQuantity());
            lepsariFromDb.getRequest().setResponsible(lepsariFromDb.getResponsible());


            lepsariFromDb.getRequest().setDate(lepsariFromDb.getDate());

            for(LepsariProduct product : lepsariFromDb.getLepsariProducts()) {
                RequestProductDTO requestProductDTO = new RequestProductDTO();
                requestProductDTO.setName(product.getName());
                requestProductDTO.setPackaging(product.getPackaging());
                requestProductDTO.setQuantity(product.getQuantity());
                requestProductDTO.setRequestId(lepsariFromDb.getRequest().getId());
                requestProductDTO.setStatus(product.getStatus());
                requestProductService.save(requestProductDTO);
            }
            requestRepo.save(lepsariFromDb.getRequest());
        }
        return lepsariRepo.save(lepsariFromDb);
    }

    @Override
    public List<Lepsari> findAll() { return lepsariRepo.findAll(); }

    @Override
    public void changeStatus(Long id, String status) {
        Lepsari lepsari = lepsariRepo.findById(id).orElse(null);
        lepsari.setStatus(status);
        if(lepsari.getRequest() != null) {
            lepsari.getRequest().setStatus(status);
            requestRepo.save(lepsari.getRequest());
        }
        lepsariRepo.save(lepsari);
    }
}

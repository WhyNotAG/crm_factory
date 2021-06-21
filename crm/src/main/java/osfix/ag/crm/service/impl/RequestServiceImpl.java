package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.InvoicingRequest;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.ShippingDocument;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.repo.InvoicingRequestRepo;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.RequestRepo;
import osfix.ag.crm.repo.ShippingDocumentRepo;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.repo.product.RequestProductRepo;
import osfix.ag.crm.service.FileStorageService;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.request.RequestViewDTO;
import osfix.ag.crm.service.mapper.RequestViewMapper;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;
    private ClientRepo clientRepo;
    private LogRepo logRepo;
    private RequestViewMapper requestViewMapper;
    private FileStorageService fileStorageService;
    private RequestProductRepo requestProductRepo;
    private InvoicingRequestRepo invoicingRequestRepo;
    private ShippingDocumentRepo shippingDocumentRepo;

    public RequestServiceImpl(RequestRepo requestRepo, ClientRepo clientRepo, LogRepo logRepo,
                              RequestViewMapper requestViewMapper, FileStorageService fileStorageService,
                              RequestProductRepo requestProductRepo, InvoicingRequestRepo invoicingRequestRepo,
                              ShippingDocumentRepo shippingDocumentRepo) {
        this.logRepo = logRepo;
        this.requestRepo = requestRepo;
        this.clientRepo = clientRepo;
        this.requestViewMapper = requestViewMapper;
        this.fileStorageService = fileStorageService;
        this.requestProductRepo = requestProductRepo;
        this.invoicingRequestRepo = invoicingRequestRepo;
        this.shippingDocumentRepo = shippingDocumentRepo;
    }

    @Override
    public List<RequestViewDTO> findAll() { return requestViewMapper.toDtoList(requestRepo.findAll()); }

    @Override
    public RequestViewDTO findById(Long id) { return requestViewMapper.fromEntity(findId(id)); }

    @Override
    public RequestViewDTO update(Long id, Request request) {
        Request requestFromDb = findId(id);
        BeanUtils.copyProperties(request,requestFromDb, "id", "client");
        loging("Изменение", "Изменение", "request", requestFromDb.getId());
        return requestViewMapper.fromEntity(requestRepo.save(requestFromDb));
    }

    @Override
    public RequestViewDTO save(Request request) {
        requestRepo.save(request);
        loging("Создание", "Создание", "request", request.getId());
        return requestViewMapper.fromEntity(request);
    }

    @Override
    public void delete(Long id) {
        loging("Удаление", "Удаление", "request", id);
        Request request = requestRepo.findById(id).orElse(null);
        request.setClient(null);
        fileStorageService.deleteInvoicing(id);
        //fileStorageService.deleteShipping(id);

        List<InvoicingRequest> invoicingRequests = request.getInvoicingRequests();
        for(InvoicingRequest invoicingRequest : invoicingRequests) {
            invoicingRequestRepo.delete(invoicingRequest);
        }

        List<RequestProduct> requestProducts = request.getRequestProducts();
        for(RequestProduct requestProduct : requestProducts) {
            requestProductRepo.delete(requestProduct);
        }

        List<ShippingDocument> shippingDocuments = request.getShippingDocuments();
        for (ShippingDocument shippingDocument : shippingDocuments) {
            shippingDocumentRepo.delete(shippingDocument);
        }

        requestRepo.delete(request);
    }

    public Request findId(Long id) {
        Request request = requestRepo.findById(id).orElse(null);
        if (request == null) {
            return null;
        } else {
            return request;
        }
    }

    @Override
    public RequestViewDTO changePaidStatus(Long id, String paidStatus) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setPaidStatus(paidStatus);
        return requestViewMapper.fromEntity(requestRepo.save(request));
    }

    @Override
    public void changeStatus(Long id, String status) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setStatus(status);
        loging("Изменение статуса", "Изменение статуса на \"" + request.getStatus() + "\"", "request", request.getId());
        requestRepo.save(request);
    }

    @Override
    public RequestViewDTO addProduct(Long id, List<String> prName, List<String> quantity, List<String> packaging) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setRequestProducts(null);
        RequestProduct requestProduct = new RequestProduct();

        for (int i = 0; i < prName.size(); i++) {
            requestProduct.setPackaging(packaging.get(i));
            requestProduct.setName(prName.get(i));
            requestProduct.setQuantity(quantity.get(i));
            request.getRequestProducts().add(requestProduct);
        }
        loging("Добавление продукта","Добавление продукта", "request", request.getId());
        return requestViewMapper.fromEntity(requestRepo.save(request));
    }

    @Override
    public RequestViewDTO copy(Long id, String factory) {
        Request request = requestRepo.findById(id).orElse(null);
        request.setFactory(factory);
        String factoryName = "";
        if(factory.equals("lepsari")) factoryName = "Лепсари";
        if(factory.equals("lemz")) factoryName = "ЛЭМЗ";
        loging("Перенос в цех", "Перенос в цех " + factoryName, "request", request.getId());
        return requestViewMapper.fromEntity(requestRepo.save(request));
    }

    @Override
    public RequestViewDTO addClient(Long requestId, Long clientId, String ltd, String inn) {
        Client client = clientRepo.findById(clientId).orElse(null);
        Request request = requestRepo.findById(requestId).orElse(null);
        request.setClient(client);
        request.setInn(inn);
        request.setLtd(ltd);
        loging("Добавление клиента", "Добавление клиента", "request", request.getId());
        return requestViewMapper.fromEntity(requestRepo.save(request));
    }

    @Override
    public List<RequestViewDTO> findByFactory(String factory) {
        return requestViewMapper.toDtoList(requestRepo.findAllByFactory(factory));
    }

    public void deletePro(Long id) {
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " заявки №" + id);
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}

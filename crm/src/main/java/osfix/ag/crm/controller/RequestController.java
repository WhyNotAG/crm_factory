package osfix.ag.crm.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import osfix.ag.crm.domain.InvoicingRequest;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.ShippingDocument;
import osfix.ag.crm.domain.UploadFileResponse;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.EmployeeDownloadDTO;
import osfix.ag.crm.service.dto.request.AddProductsDTO;
import osfix.ag.crm.service.dto.request.RequestDTO;
import osfix.ag.crm.service.dto.request.RequestViewDTO;
import osfix.ag.crm.service.mapper.RequestMapper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    private RequestService requestService;
    private RequestMapper requestMapper;
    private FileControllerWithoutDB fileControllerWithoutDB;
    private JavaMailSender javaMailSender;

    public RequestController(RequestService requestService, RequestMapper requestMapper,
                             FileControllerWithoutDB fileControllerWithoutDB, JavaMailSender javaMailSender) {
        this.requestService = requestService;
        this.requestMapper = requestMapper;
        this.fileControllerWithoutDB = fileControllerWithoutDB;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestViewDTO>> getAllClients() {
        return ResponseEntity.ok().body(requestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestViewDTO> getClient(@PathVariable(name = "id") long id) {
        return  ResponseEntity.ok().body(requestService.findById(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping("/")
    public ResponseEntity<RequestViewDTO> create(@RequestBody RequestDTO request) {
        RequestViewDTO result = requestService.save(requestMapper.toEntity(request));
        return ResponseEntity.ok().body(result);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ResponseEntity<RequestViewDTO> update(@PathVariable(name = "id") Long id, @RequestBody RequestDTO request) {
        RequestViewDTO result =  requestService.update(id, requestMapper.toEntity(request));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/paid/{id}/{paid}")
    public ResponseEntity<RequestViewDTO> changePaidStatus(@PathVariable(name = "id") Long id,
                                                           @PathVariable(name = "paid") String paid) {
        return ResponseEntity.ok().body(requestService.changePaidStatus(id, paid));
    }

    @PutMapping("/status/{id}")
    public void changeStatus(@PathVariable(name = "id") Long id, @RequestBody RequestDTO request) {
        requestService.changeStatus(id, request.getStatus());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { requestService.delete(id);}

    @PostMapping("/{id}")
    public ResponseEntity<RequestViewDTO> addProducts(@PathVariable(name = "id") Long id, @RequestBody AddProductsDTO addProductsDTO) {
        RequestViewDTO request = requestService.addProduct(id, addProductsDTO.getProductsName(), addProductsDTO.getQuantity(), addProductsDTO.getPackaging());
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/transfer/{id}/{factory}")
    public ResponseEntity<RequestViewDTO> transfer(@PathVariable(name = "id") Long id, @PathVariable(name = "factory") String factory) {
        return ResponseEntity.ok().body(requestService.copy(id,factory));
    }

    @GetMapping("/factory/{factory}")
    public ResponseEntity<List<RequestViewDTO>> findByFactory(@PathVariable(name = "factory") String factory) {
        return ResponseEntity.ok().body(requestService.findByFactory(factory));
    }

    @DeleteMapping("/pro/{id}")
    public void deletePro(@PathVariable(name = "id") Long id) {
        requestService.deletePro(id);
    }

    @GetMapping("/addClient/{request}/{client}/{email}/{inn}/{ltd}")
    public ResponseEntity<RequestViewDTO> addClient(@PathVariable(name = "client") Long clientId,
                                                    @PathVariable(name = "request") Long requestId,
                                                    @PathVariable(name = "email") String email,
                                                    @PathVariable(name = "inn") String inn,
                                                    @PathVariable(name = "ltd") String ltd){
        RequestViewDTO result = requestService.addClient(requestId, clientId, ltd, inn);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Создание заявки №" + result.getId());
        msg.setText("Это письмо сформированно автоматически!" +
                "\nЗаявка №" + result.getId() + " была создана" +
                "\nКлиент: " + result.getClient().getName() +
                "\nИНН: " + inn +
                "\nООО: " + ltd +
                "\nНеобходимо выставить счет.");

        javaMailSender.send(msg);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/invoicing/{id}/")
    public ResponseEntity<RequestViewDTO> addInvocing(@PathVariable(name = "id") Long id,
                                                      @ModelAttribute EmployeeDownloadDTO employee) throws MessagingException, URISyntaxException, MalformedURLException {
        MultipartFile[] files = employee.getFiles();
        if (files == null) {
            return null;
        }
        List<UploadFileResponse> response = fileControllerWithoutDB.invoicngUploadMultipleFiles(id, files);

        return ResponseEntity.ok().body(requestService.findById(id));
    }

    @PostMapping("/shipping/{id}/")
    public ResponseEntity<RequestViewDTO> addShipping(@PathVariable(name = "id") Long id,
                                                      @ModelAttribute EmployeeDownloadDTO employee) throws MessagingException, URISyntaxException, MalformedURLException {
        MultipartFile[] files = employee.getFiles();
        if (files == null) {
            return null;
        }
        List<UploadFileResponse> response = fileControllerWithoutDB.shippingUploadMultipleFiles(id, files);
        RequestViewDTO request = requestService.findById(id);
        return ResponseEntity.ok().body(requestService.findById(id));
    }

    @GetMapping("/invoicing/{id}/{email}/")
    public void sendInvoicing(@PathVariable(name = "id") Long id,
                              @PathVariable(name = "email") String email) throws URISyntaxException, MessagingException {
        List<InvoicingRequest> invoicingRequests = requestService.findById(id).getInvoicingRequest();
        String path = invoicingRequests.get(invoicingRequests.size() - 1).getUrl();
        path = path.replace("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/", "/");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Счет по заявке.");
        helper.setText("Это письмо сформированно автоматически!" +
                "\nПо вашей заявке был выставлен счет.");
        File file = new File( "/uploads/" + path);
        helper.addAttachment("Счет.pdf", file);
        javaMailSender.send(message);
    }

    @GetMapping("/shipping/{id}/{email}/")
    public void sendShipping(@PathVariable(name = "id") Long id,
                              @PathVariable(name = "email") String email) throws URISyntaxException, MessagingException {
        List<ShippingDocument> shippingDocuments = requestService.findById(id).getShippingDocuments();
        String path = shippingDocuments.get(shippingDocuments.size() - 1).getUrl();
        path = path.replace("https://194-58-104-192.ovz.vps.regruhosting.ru:8443/api/v1/fileWithoutDB/downloadFile/", "/");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Отгрузочные документы.");
        helper.setText("Это письмо сформированно автоматически!" +
                "\nПо вашей заявке были выставленны отгрузочные документы.");
        File file = new File( "/uploads/" + path);
        helper.addAttachment("Отгрузка.pdf", file);
        javaMailSender.send(message);
    }
}

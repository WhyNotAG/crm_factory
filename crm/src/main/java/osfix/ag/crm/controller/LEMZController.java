package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.dto.request.FactoryDTO;
import osfix.ag.crm.service.mapper.LemzMapper;

import javax.persistence.Column;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/v1/lemz")
public class LEMZController {
    private LEMZService lemzService;
    private LemzMapper lemzMapper;

    public LEMZController(LEMZService lemzService, LemzMapper lemzMapper) {
        this.lemzService = lemzService;
        this.lemzMapper = lemzMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<LEMZ>> getAll() { return ResponseEntity.ok().body(lemzService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<LEMZ> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lemzService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<LEMZ> add(@RequestBody FactoryDTO lemz) {
        return ResponseEntity.ok().body(lemzService.save(lemzMapper.toEntity(lemz)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LEMZ> update(@PathVariable(name = "id") Long id, @RequestBody FactoryDTO lemz) {
        return ResponseEntity.ok().body(lemzService.update(id, lemzMapper.toEntity(lemz)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        lemzService.delete(id);
    }

    @PutMapping("/status/{id}")
    public void changeStatus(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO lemz) {
        lemzService.changeStatus(id, lemz.getStatus());
    }
}

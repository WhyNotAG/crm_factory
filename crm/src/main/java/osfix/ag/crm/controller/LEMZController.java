package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import javax.persistence.Column;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/v1/lemz")
public class LEMZController {
    private LEMZService lemzService;

    public LEMZController(LEMZService lemzService) {
        this.lemzService = lemzService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LEMZ>> getAll() { return ResponseEntity.ok().body(lemzService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<LEMZ> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lemzService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<LEMZ> add(@RequestBody LEMZ lemz) {
        return ResponseEntity.ok().body(lemzService.save(lemz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LEMZ> update(@PathVariable(name = "id") Long id, @RequestBody LEMZ lemz) {
        return ResponseEntity.ok().body(lemzService.update(id, lemz));
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

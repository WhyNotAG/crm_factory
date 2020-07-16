package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.service.LepsariService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lepsari")
public class LepsariController {

    private LepsariService lepsariService;

    public LepsariController(LepsariService lepsariService) {
        this.lepsariService = lepsariService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Lepsari>> getAll() { return ResponseEntity.ok().body(lepsariService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Lepsari> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lepsariService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Lepsari> add(@RequestBody Lepsari lepsari) {
        return ResponseEntity.ok().body(lepsariService.save(lepsari));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lepsari> update(@PathVariable(name = "id") Long id, @RequestBody Lepsari lepsari) {
        return ResponseEntity.ok().body(lepsariService.update(id, lepsari));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        lepsariService.delete(id);
    }

    @PutMapping("/status/{id}")
    public void changeStatus(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO lepsari) {
        lepsariService.changeStatus(id, lepsari.getStatus());
    }
}

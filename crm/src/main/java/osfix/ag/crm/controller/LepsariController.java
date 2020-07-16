package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.service.LepsariService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.dto.request.FactoryDTO;
import osfix.ag.crm.service.mapper.LepsariMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lepsari")
public class LepsariController {

    private LepsariService lepsariService;
    private LepsariMapper lepsariMapper;

    public LepsariController(LepsariService lepsariService, LepsariMapper lepsariMapper) {
        this.lepsariService = lepsariService;
        this.lepsariMapper = lepsariMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Lepsari>> getAll() { return ResponseEntity.ok().body(lepsariService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Lepsari> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lepsariService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Lepsari> add(@RequestBody FactoryDTO lepsari) {
        return ResponseEntity.ok().body(lepsariService.save(lepsariMapper.toEntity(lepsari)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lepsari> update(@PathVariable(name = "id") Long id, @RequestBody FactoryDTO lepsari) {
        return ResponseEntity.ok().body(lepsariService.update(id, lepsariMapper.toEntity(lepsari)));
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

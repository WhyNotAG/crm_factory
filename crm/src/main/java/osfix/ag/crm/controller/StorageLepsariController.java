package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.StorageLepsari;
import osfix.ag.crm.service.StorageLepsariService;

import java.util.List;

@RestController
@RequestMapping("api/v1/lepsari_storage")
public class StorageLepsariController {
    private StorageLepsariService storageLepsariService;

    public StorageLepsariController(StorageLepsariService storageLepsariService) {
        this.storageLepsariService = storageLepsariService;
    }

    @GetMapping("/")
    public ResponseEntity<List<StorageLepsari>> findAll() { return ResponseEntity.ok().body(storageLepsariService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<StorageLepsari> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(storageLepsariService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<StorageLepsari> add(@RequestBody StorageLepsari storageLepsari) {
        return ResponseEntity.ok().body(storageLepsariService.save(storageLepsari));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageLepsari> update(@PathVariable(name = "id") Long id, @RequestBody StorageLepsari storageLepsari) {
        return ResponseEntity.ok().body(storageLepsariService.update(id, storageLepsari));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        storageLepsariService.delete(id);
    }
}

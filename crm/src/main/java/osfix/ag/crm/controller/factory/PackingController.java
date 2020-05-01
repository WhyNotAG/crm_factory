package osfix.ag.crm.controller.factory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.factory.EquipmentProduct;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.service.PackingService;
import osfix.ag.crm.service.dto.factory.EquipmentProductDTO;
import osfix.ag.crm.service.dto.factory.PackingDTO;
import osfix.ag.crm.service.mapper.factory.PackingMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/packing")
public class PackingController {
    PackingMapper packingMapper;
    PackingService packingService;

    PackingController(PackingService packingService, PackingMapper packingMapper) {
        this.packingMapper = packingMapper;
        this.packingService = packingService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Packing>> findAll() {
        return ResponseEntity.ok().body(packingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Packing> findAllById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(packingService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Packing> add(@RequestBody PackingDTO packingDTO) {
        return ResponseEntity.ok().body(packingService.save(packingMapper.toEntity(packingDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Packing> update(@PathVariable(name = "id") Long id, @RequestBody PackingDTO packingDTO) {
        return ResponseEntity.ok().body(packingService.update(id,
                packingMapper.toEntity(packingDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        packingService.delete(id);
    }

}

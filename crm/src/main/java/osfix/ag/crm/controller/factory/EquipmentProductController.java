package osfix.ag.crm.controller.factory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.factory.EquipmentProduct;
import osfix.ag.crm.service.EquipmentProductsService;
import osfix.ag.crm.service.dto.factory.EquipmentProductDTO;
import osfix.ag.crm.service.mapper.factory.EquipmentProductMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment_product")
public class EquipmentProductController {
    EquipmentProductsService equipmentProductsService;
    EquipmentProductMapper equipmentProductMapper;

    public EquipmentProductController(EquipmentProductsService equipmentProductsService, EquipmentProductMapper equipmentProductMapper) {
        this.equipmentProductsService = equipmentProductsService;
        this.equipmentProductMapper = equipmentProductMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<EquipmentProduct>> findAll() {
        return ResponseEntity.ok().body(equipmentProductsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentProduct> findAllById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(equipmentProductsService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<EquipmentProduct> add(@RequestBody EquipmentProductDTO equipmentProduct) {
        return ResponseEntity.ok().body(equipmentProductsService.save(equipmentProductMapper.toEntity(equipmentProduct)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentProduct> update(@PathVariable(name = "id") Long id, @RequestBody EquipmentProductDTO equipmentProduct) {
        return ResponseEntity.ok().body(equipmentProductsService.update(id,
                equipmentProductMapper.toEntity(equipmentProduct)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        equipmentProductsService.delete(id);
    }

}

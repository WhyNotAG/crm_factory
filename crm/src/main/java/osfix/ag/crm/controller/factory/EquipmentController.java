package osfix.ag.crm.controller.factory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.factory.Equipment;
import osfix.ag.crm.service.EquipmentService;
import osfix.ag.crm.service.dto.factory.EquipmentDTO;
import osfix.ag.crm.service.mapper.factory.EquipmentMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {
    EquipmentService equipmentService;
    EquipmentMapper equipmentMapper;

    public EquipmentController(EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Equipment>> findAll() {
        return ResponseEntity.ok().body(equipmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> findAllById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(equipmentService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Equipment> add(@RequestBody EquipmentDTO equipment) {
        return ResponseEntity.ok().body(equipmentService.save(equipmentMapper.toEntity(equipment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable(name = "id") Long id, @RequestBody EquipmentDTO equipment) {
        return ResponseEntity.ok().body(equipmentService.update(id,
                equipmentMapper.toEntity(equipment)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        equipmentService.delete(id);
    }

    @PostMapping("/name/")
    public ResponseEntity<List<Equipment>> findAllByFactoryName(@RequestBody EquipmentDTO equipment) {
        return ResponseEntity.ok().body(equipmentService.findAllByFactoryName(equipment.getName()));
    }
}

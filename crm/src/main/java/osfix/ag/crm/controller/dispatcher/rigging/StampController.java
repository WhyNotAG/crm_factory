package osfix.ag.crm.controller.dispatcher.rigging;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.service.StampPartService;
import osfix.ag.crm.service.StampService;
import osfix.ag.crm.service.dto.RiggingDTO;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.rigging.StampPartMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stamp")
public class StampController {
    private StampService stampService;
    private StampPartService stampPartService;
    private StampPartMapper stampPartMapper;

    public StampController(StampService stampService, StampPartService stampPartService, StampPartMapper stampPartMapper) {
        this.stampService = stampService;
        this.stampPartService = stampPartService;
        this.stampPartMapper = stampPartMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Stamp>> findAll() {
        return ResponseEntity.ok().body(stampService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stamp> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(stampService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Stamp> add(@RequestBody Stamp stamp) {
        return ResponseEntity.ok().body(stampService.save(stamp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stamp> update(@PathVariable(name = "id") Long id, @RequestBody Stamp stamp) {
        return ResponseEntity.ok().body(stampService.update(id, stamp));
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<Stamp> changeColor(@PathVariable(name = "id") Long id, @RequestBody RiggingDTO color) {
        return ResponseEntity.ok().body(stampService.changeColor(id, color.getColor()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        stampService.delete(id);
    }

    //Part controller
    @GetMapping("/part/")
    public ResponseEntity<List<StampPart>> findAllParts() {
        return ResponseEntity.ok().body(stampPartService.findAll());
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<StampPart> findPartById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(stampPartService.findById(id));
    }

    @PostMapping("/part/")
    public ResponseEntity<StampPart> addPart(@RequestBody RiggingPartDTO stampPart) {
        return ResponseEntity.ok().body(stampPartService.save(stampPartMapper.toEntity(stampPart)));
    }

    @PutMapping("/part/{id}")
    public ResponseEntity<StampPart> updatePart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO stampPart) {
        return ResponseEntity.ok().body(stampPartService.update(id, stampPartMapper.toEntity(stampPart)));
    }

    @PutMapping("/part/color/{id}")
    public ResponseEntity<StampPart> changeColorPart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO color) {
        return ResponseEntity.ok().body(stampPartService.changeColor(id, color.getColor()));
    }

    @DeleteMapping("/part/{id}")
    public void deletePart(@PathVariable(name = "id") Long id) {
        stampPartService.delete(id);
    }
}

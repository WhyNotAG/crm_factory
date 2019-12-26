package osfix.ag.crm.controller.dispatcher.rigging;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.service.PressPartService;
import osfix.ag.crm.service.PressService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.rigging.PressPartMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/press")
public class PressController {
    private PressService pressService;
    private PressPartService pressPartService;
    private PressPartMapper pressPartMapper;

    public PressController(PressService pressService, PressPartService pressPartService, PressPartMapper pressPartMapper) {
        this.pressService = pressService;
        this.pressPartService = pressPartService;
        this.pressPartMapper = pressPartMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Press>> findAll() {
        return ResponseEntity.ok().body(pressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Press> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(pressService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Press> add(@RequestBody Press press) {
        return ResponseEntity.ok().body(pressService.save(press));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Press> update(@PathVariable(name = "id") Long id, @RequestBody Press press) {
        return ResponseEntity.ok().body(pressService.update(id, press));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        pressService.delete(id);
    }

    //Part controller
    @GetMapping("/part/")
    public ResponseEntity<List<PressPart>> findAllParts() {
        return ResponseEntity.ok().body(pressPartService.findAll());
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<PressPart> findPartById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(pressPartService.findById(id));
    }

    @PostMapping("/part/")
    public ResponseEntity<PressPart> addPart(@RequestBody RiggingPartDTO pressPart) {
        return ResponseEntity.ok().body(pressPartService.save(pressPartMapper.toEntity(pressPart)));
    }

    @PutMapping("/part/{id}")
    public ResponseEntity<PressPart> updatePart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO pressPart) {
        return ResponseEntity.ok().body(pressPartService.update(id, pressPartMapper.toEntity(pressPart)));
    }

    @DeleteMapping("/part/{id}")
    public void deletePart(@PathVariable(name = "id") Long id) {
        pressPartService.delete(id);
    }
}

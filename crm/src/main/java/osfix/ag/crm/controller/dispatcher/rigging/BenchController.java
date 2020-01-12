package osfix.ag.crm.controller.dispatcher.rigging;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.rigging.Bench;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.service.BenchPartService;
import osfix.ag.crm.service.BenchService;
import osfix.ag.crm.service.dto.RiggingDTO;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.rigging.BenchPartMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bench")
public class BenchController {
    private BenchService benchService;
    private BenchPartService benchPartService;
    private BenchPartMapper benchPartMapper;

    public BenchController(BenchService benchService, BenchPartService benchPartService, BenchPartMapper benchPartMapper) {
        this.benchService = benchService;
        this.benchPartService = benchPartService;
        this.benchPartMapper = benchPartMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bench>> findAll() {
        return ResponseEntity.ok().body(benchService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bench> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(benchService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Bench> add(@RequestBody Bench bench) {
        return ResponseEntity.ok().body(benchService.save(bench));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bench> update(@PathVariable(name = "id") Long id, @RequestBody Bench bench) {
        return ResponseEntity.ok().body(benchService.update(id, bench));
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<Bench> changeColor(@PathVariable(name = "id") Long id, @RequestBody RiggingDTO color) {
        return ResponseEntity.ok().body(benchService.changeColor(id, color.getColor()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        benchService.delete(id);
    }

    //Part controller
    @GetMapping("/part/")
    public ResponseEntity<List<BenchPart>> findAllParts() {
        return ResponseEntity.ok().body(benchPartService.findAll());
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<BenchPart> findPartById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(benchPartService.findById(id));
    }

    @PostMapping("/part/")
    public ResponseEntity<BenchPart> addPart(@RequestBody RiggingPartDTO benchPart) {
        return ResponseEntity.ok().body(benchPartService.save(benchPartMapper.toEntity(benchPart)));
    }

    @PutMapping("/part/{id}")
    public ResponseEntity<BenchPart> updatePart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO benchPart) {
        return ResponseEntity.ok().body(benchPartService.update(id, benchPartMapper.toEntity(benchPart)));
    }

    @PutMapping("/part/color/{id}")
    public ResponseEntity<BenchPart> changeColorPart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO color) {
        return ResponseEntity.ok().body(benchPartService.changeColor(id, color.getColor()));
    }

    @DeleteMapping("/part/{id}")
    public void deletePart(@PathVariable(name = "id") Long id) {
        benchPartService.delete(id);
    }
}

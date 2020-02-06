package osfix.ag.crm.controller.dispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.Detail;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.service.DetailPartService;
import osfix.ag.crm.service.DetailService;
import osfix.ag.crm.service.dto.RiggingPartDTO;
import osfix.ag.crm.service.mapper.rigging.DetailPartMapper;

import java.util.List;

@RestController
@RequestMapping("api/v1/detail")
public class DetailController {

    private DetailService detailService;
    private DetailPartService detailPartService;
    private DetailPartMapper detailPartMapper;

    public DetailController(DetailService detailService, DetailPartMapper detailPartMapper, DetailPartService detailPartService) {
        this.detailService = detailService;
        this.detailPartService = detailPartService;
        this.detailPartMapper = detailPartMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Detail>> findAll() {
        return ResponseEntity.ok().body(detailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(detailService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Detail> add(@RequestBody Detail detail) {
        return ResponseEntity.ok().body(detailService.save(detail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detail> update(@PathVariable(name = "id") Long id, @RequestBody Detail detail) {
        return ResponseEntity.ok().body(detailService.update(id, detail));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        detailService.delete(id);
    }
    //Part controller
    @GetMapping("/part/")
    public ResponseEntity<List<DetailPart>> findAllParts() {
        return ResponseEntity.ok().body(detailPartService.findAll());
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<DetailPart> findPartById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(detailPartService.findById(id));
    }

    @PostMapping("/part/")
    public ResponseEntity<DetailPart> addPart(@RequestBody RiggingPartDTO detailPart) {
        return ResponseEntity.ok().body(detailPartService.save(detailPartMapper.toEntity(detailPart)));
    }

    @PutMapping("/part/{id}")
    public ResponseEntity<DetailPart> updatePart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO detailPart) {
        return ResponseEntity.ok().body(detailPartService.update(id, detailPartMapper.toEntity(detailPart)));
    }

    @PutMapping("/part/color/{id}")
    public ResponseEntity<DetailPart> changeColorPart(@PathVariable(name = "id") Long id, @RequestBody RiggingPartDTO color) {
        return ResponseEntity.ok().body(detailPartService.changeColor(id, color.getColor()));
    }

    @DeleteMapping("/part/{id}")
    public void deletePart(@PathVariable(name = "id") Long id) {
        detailPartService.delete(id);
    }


}

package osfix.ag.crm.controller.dispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.Detail;
import osfix.ag.crm.service.DetailService;

import java.util.List;

@RestController
@RequestMapping("api/v1/detail")
public class DetailController {
    private DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
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


}

package osfix.ag.crm.controller.dispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.ShippingRegistry;
import osfix.ag.crm.service.ShippingRegistryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingRegistryController {
    ShippingRegistryService shippingRegistryService;

    public ShippingRegistryController(ShippingRegistryService shippingRegistryService) {
        this.shippingRegistryService = shippingRegistryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ShippingRegistry>> findAll() {
        return ResponseEntity.ok().body(shippingRegistryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingRegistry> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(shippingRegistryService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<ShippingRegistry> add(@RequestBody ShippingRegistry shippingRegistry) {
        return ResponseEntity.ok().body(shippingRegistryService.save(shippingRegistry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingRegistry> update(@PathVariable(name = "id") Long id, @RequestBody ShippingRegistry shippingRegistry) {
        return ResponseEntity.ok().body(shippingRegistryService.update(id, shippingRegistry));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        shippingRegistryService.delete(id);
    }
}

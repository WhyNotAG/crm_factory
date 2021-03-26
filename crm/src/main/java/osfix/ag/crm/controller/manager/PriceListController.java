package osfix.ag.crm.controller.manager;

import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.Prices;
import osfix.ag.crm.service.PriceListService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/priceList")
public class PriceListController {
    PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/")
    public List<Prices> getAll() { return priceListService.findAll();}

    @GetMapping("/{id}")
    public Prices getById(@PathVariable(name = "id") long id) {return priceListService.findById(id);}

    @PostMapping()
    public Prices create(@RequestBody Prices prices) {
        return priceListService.save(prices);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { priceListService.delete(id);}

    @PutMapping("/{id}")
    public Prices update(@PathVariable(name = "id") Long id, @RequestBody Prices prices) {
        return priceListService.update(id, prices);
    }
}

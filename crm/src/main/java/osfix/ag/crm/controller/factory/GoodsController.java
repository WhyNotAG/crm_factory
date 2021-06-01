package osfix.ag.crm.controller.factory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.service.GoodsService;
import osfix.ag.crm.service.dto.factory.GoodsDTO;
import osfix.ag.crm.service.dto.factory.GoodsViewDTO;
import osfix.ag.crm.service.mapper.factory.GoodsMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goods")
public class GoodsController {
    GoodsService goodsService;
    GoodsMapper goodsMapper;

    public GoodsController(GoodsService goodsService, GoodsMapper goodsMapper) {
        this.goodsService = goodsService;
        this.goodsMapper = goodsMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<GoodsViewDTO>> findAll() {
        return ResponseEntity.ok().body(goodsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsViewDTO> findAllById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(goodsService.findById(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<GoodsViewDTO>> findAllByProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(goodsService.findAllByProduct(id));
    }

    @PostMapping("/")
    public ResponseEntity<GoodsViewDTO> add(@RequestBody GoodsDTO goodsDTO) {
        return ResponseEntity.ok().body(goodsService.add(goodsMapper.toEntity(goodsDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoodsViewDTO> update(@PathVariable(name = "id") Long id, @RequestBody GoodsDTO goodsDTO) {
        return ResponseEntity.ok().body(goodsService.update(id,
                goodsMapper.toEntity(goodsDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        goodsService.delete(id);
    }
}

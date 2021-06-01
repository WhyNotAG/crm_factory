package osfix.ag.crm.service;

import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.service.dto.factory.GoodsViewDTO;

import java.util.List;

public interface GoodsService {
    List<GoodsViewDTO> findAll();
    List<GoodsViewDTO> findAllByProduct(Long productId);
    GoodsViewDTO add(Goods goods);
    GoodsViewDTO update(Long id, Goods goods);
    GoodsViewDTO findById(Long id);
    void delete(Long id);
}

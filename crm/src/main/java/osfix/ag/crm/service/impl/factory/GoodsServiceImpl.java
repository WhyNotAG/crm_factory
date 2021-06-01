package osfix.ag.crm.service.impl.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.factory.GoodsRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.GoodsService;
import osfix.ag.crm.service.dto.factory.GoodsViewDTO;
import osfix.ag.crm.service.mapper.factory.GoodsViewMapper;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepo goodsRepo;
    private ProductRepo productRepo;
    private GoodsViewMapper goodsViewMapper;

    public GoodsServiceImpl(GoodsRepo goodsRepo, ProductRepo productRepo, GoodsViewMapper goodsViewMapper) {
        this.goodsRepo = goodsRepo;
        this.productRepo = productRepo;
        this.goodsViewMapper = goodsViewMapper;
    }

    @Override
    public List<GoodsViewDTO> findAll() {
        return goodsViewMapper.toDtoList(goodsRepo.findAll());
    }

    @Override
    public List<GoodsViewDTO> findAllByProduct(Long productId) {
        return goodsViewMapper.toDtoList(goodsRepo.findAllByProduct(productRepo.findById(productId).orElse(null)));
    }

    @Override
    public GoodsViewDTO add(Goods goods) {
        return goodsViewMapper.fromEntity(goodsRepo.save(goods));
    }

    @Override
    public GoodsViewDTO update(Long id, Goods goods) {
        Goods goodsFromDB = goodsRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(goods, goodsFromDB, "id");
        return goodsViewMapper.fromEntity(goodsRepo.save(goodsFromDB));
    }

    @Override
    public GoodsViewDTO findById(Long id) {
        return goodsViewMapper.fromEntity(goodsRepo.findById(id).orElse(null));
    }

    @Override
    public void delete(Long id) {
        goodsRepo.deleteById(id);
    }
}

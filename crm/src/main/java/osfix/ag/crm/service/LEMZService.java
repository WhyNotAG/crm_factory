package osfix.ag.crm.service;

import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.product.Product;

import java.util.List;

public interface LEMZService {
    LEMZ findById(Long id);
    LEMZ save(LEMZ lemz);
    void delete(Long id);
    LEMZ update(Long id, LEMZ lemz);
    List<LEMZ> findAll();
    void changeStatus(Long id, String status);
}

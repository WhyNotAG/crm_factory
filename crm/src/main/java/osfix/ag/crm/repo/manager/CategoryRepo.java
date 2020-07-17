package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.Category;
import osfix.ag.crm.domain.manager.Client;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAllByType(String Type);
}

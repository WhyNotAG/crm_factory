package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.admin.FileModel;

@Repository
public interface FileRepo extends JpaRepository<FileModel, Long> {


}
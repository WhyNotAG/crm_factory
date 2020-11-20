package osfix.ag.crm.repo.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.user.User;

import java.util.List;
import java.util.Set;


@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Page<Client> findAll(Pageable pageable);
    Page<Client> findAllByCategory_Name(String name, Pageable pageable);

    Page<Client> findAllByCategory_NameAndClientType(String name, String clientType, Pageable pageable);
//    Set<Client> findAllByCategory_NameAndClientTypeAndUserIsNull(String name, String clientType);
//    Set<Client> findAllByCategory_NameAndClientTypeAndUser(String name, String clientType, User user);


    Page<Client> findAllByCategory_NameAndClientTypeAndType(String name, String clientType, String type, Pageable pageable);
    Set<Client> findAllByNameIgnoreCaseContainsAndTypeOrCommentIgnoreCaseContainsAndTypeOrSiteIgnoreCaseContainsAndType(String substring, String type, String substring2, String type2, String substring3, String type3);


    Page<Client> findAllByCategory_NameInAndClientTypeAndType(List<String> name, String clientType, String type, Pageable pageable);
//    Set<Client> findAllByCategory_NameInAndClientTypeAndTypeAndUserIsNull(List<String> name, String clientType, String type, Pageable pageable);
//    Set<Client> findAllByCategory_NameInAndClientTypeAndTypeAndUser(List<String> name, String clientType, String type, User user, Pageable pageable);

    Set<Client>  findAllByNameIgnoreCaseContainsOrCommentContainsOrSiteContains(String name, String comment, String site);
}

package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import da.java.common.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(@Param("email")String email);
}

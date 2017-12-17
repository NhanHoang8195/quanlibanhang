package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Account;

@RepositoryRestResource
public interface LoginRepository extends JpaRepository<Account, Long> {
    public Account findByEmail(@Param("email")String email);

}

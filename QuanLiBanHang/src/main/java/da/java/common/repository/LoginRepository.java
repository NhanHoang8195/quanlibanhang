package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Account;

@Repository
public interface LoginRepository extends JpaRepository<Account, Long> {
    public Account findByEmail(String email);

}

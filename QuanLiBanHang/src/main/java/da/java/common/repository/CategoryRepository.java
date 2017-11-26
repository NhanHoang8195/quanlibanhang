package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Account;
import da.java.common.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

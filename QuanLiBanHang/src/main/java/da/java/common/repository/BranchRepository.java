package da.java.common.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import da.java.common.entities.Branch;
import da.java.common.entities.Food;

@RepositoryRestResource
public interface BranchRepository extends JpaRepository<Branch, Long> {
}

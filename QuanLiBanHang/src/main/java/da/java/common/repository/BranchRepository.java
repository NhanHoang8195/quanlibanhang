package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import da.java.common.entities.Branch;

@RepositoryRestResource
public interface BranchRepository extends JpaRepository<Branch, Long> {
}

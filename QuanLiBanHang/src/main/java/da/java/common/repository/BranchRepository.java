package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import da.java.common.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}

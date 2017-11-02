package da.java.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Role;
import da.java.common.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(RoleName name);
}

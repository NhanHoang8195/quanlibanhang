package da.java.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import da.java.common.enums.RoleName;


@Entity
@Table(name = "role")
public class Role implements Serializable {

    /**
     * The Constant serialVersionUID
     */
    private static final long serialVersionUID = 8215580896957476823L;
    
    /** The Id auto Increment*/
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    
    /** The role name*/
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;
    
    /** The relationship 1:1 with account table*/
   
    //============ GETTER-SETTER-CONSTRUCTOR=============
    
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    
    public Role(Long roleId, RoleName roleName, List<Account> account) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role() {
        super();
    }
}

package da.java.common.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;
    
    
    @ManyToMany(mappedBy = "roles", cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Account> account;
    /** The relationship 1:1 with account table*/
   
    //============ GETTER-SETTER-CONSTRUCTOR=============
    
    
    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
    public Role(Long roleId, RoleName roleName, Set<Account> account) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
        this.account = account;
    }
    
    
    public Role() {
        super();
    }
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
    public Set<Account> getAccount() {
        return account;
    }
    public void setAccount(Set<Account> account) {
        this.account = account;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Role)
        {
            sameSame = this.roleName.equals(((Role) object).roleName);
        }

        return sameSame;
    }
}

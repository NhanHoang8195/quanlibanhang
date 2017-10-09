package da.java.common.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
    @Column(name = "role_name")
    private String roleName;
    
    /** The relationship 1:1 with account table*/
    @JsonIgnore
    @OneToOne(mappedBy = "roleId", cascade = CascadeType.ALL)
    private Account account;

    //============ GETTER-SETTER-CONSTRUCTOR=============
    
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role(Long roleId, String roleName, Account account) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
        this.account = account;
    }

    public Role() {
        super();
    }
}

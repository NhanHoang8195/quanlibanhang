package da.java.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    /**
     * The Constant serialVersionUID
     */
    private static final long serialVersionUID = 2557811486070804430L;
    
    /** The Id auto increment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    
    /** The username*/
    @Column(name = "username", nullable = false, updatable=false, unique=true)
    private String username;
    
    /** The password*/
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    
    /** Phone number of an user*/
    @Column(name = "phone", nullable = false, unique=true)
    private String phone;
    
    /** Address of an user*/
    @Column(name = "address")
    private String address;
    
    /** Real name of an user*/
    @Column(name = "real_name")
    private String realName;
    
    /** RoleId of an user*/
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    

    public Account(Long accountId, String username, String password, String phone, String address, String realName,
            Role roleId) {
        super();
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.realName = realName;
        this.roleId = roleId;
    }

    public Account() {
        super();
    }
    
}

package da.java.common.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
    @Email
    @NotEmpty
    @Column(name = "email", nullable = false, updatable=false, unique=true)
    private String email;
    
    /** The password*/
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;
    
    /** Phone number of an user*/
    @NotEmpty
    @Column(name = "phone", nullable = false, unique=true)
    private String phone;
    
    /** Address of an user*/
    @Column(name = "address")
    private String address;
    
    /** Real name of an user*/
    @NotEmpty
    @Column(name = "real_name", nullable = false)
    private String realName;
    
    /** RoleId of an user*/
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Role roleId;

    public Account(Long accountId, String email, String password, String phone, String address, String realName,
            Role roleId) {
        super();
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.realName = realName;
        this.roleId = roleId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public Account() {
        super();
    }
    
}

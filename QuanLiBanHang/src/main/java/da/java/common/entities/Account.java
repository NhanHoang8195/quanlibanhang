package da.java.common.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

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
    @Column(name = "email", unique=true)
    private String email;
    
    /** The password*/
    @Column(name = "password")
    private String password;
    
    /** Phone number of an user*/
    @Column(name = "phone", unique=true)
    private String phone;
    
    /** Address of an user*/
    @Column(name = "address")
    private String address;
    
    /** Real name of an user*/
    @Column(name = "real_name")
    private String realName;
    
    /** RoleId of an user*/
    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;
    
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch; 
    
    public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }

    public Account(Long accountId, String email, String password, String phone, String address, String realName,
            Set<Role> roles) {
        super();
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.realName = realName;
        this.roles = roles;
    }

    public Account() {
        super();
    }

}

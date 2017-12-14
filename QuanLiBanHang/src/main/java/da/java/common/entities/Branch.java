package da.java.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "branch")
public class Branch implements Serializable {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = 7929246720600082236L;

    /** The Id auto increment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchId;
    
    /** Name of a branch*/
    @Column(name = "name")
    private String name;
    
    /** Phone of a branch*/
    @Column(name = "phone")
    private String phone;
    
    /** Address of a branch*/
    @Column(name = "address")
    private String address;
    
    /** Images of a branch*/
    @Column(name = "image")
    private String image;
    
    @ManyToMany(cascade = { CascadeType.MERGE})
    @JoinTable(name = "branch_food", 
    joinColumns = @JoinColumn(name = "branch_id"),
    inverseJoinColumns = @JoinColumn(name = "food_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Food> foods;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    public Branch(Long branchId, String name, String phone, String address, String image, List<Food> foods) {
        super();
        this.branchId = branchId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.foods = foods;
    }

    public Branch(String name, String phone, String address, String image) {
        super();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }
    
    public Branch() {
        super();
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Branch)
        {
            sameSame = this.name.equals(((Branch) object).name);
        }

        return sameSame;
    }
    
}

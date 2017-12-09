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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "food")
@JsonInclude(value = Include.NON_NULL)
public class Food implements Serializable {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = 2675276957139638797L;
    
    /** The Id auto increment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;
    
    /** Name of the food*/
    @Column(name = "food_name", nullable = false, unique = true)
    private String foodName;

    /** Price of the food*/
    @Column(name = "price", nullable = false)
    private Long price;
    
    /** Image of the food*/
    @Column(name = "image")
    private String image;
    
    /** Many To Many relationship with product_order table*/
    @JsonIgnore
    @ManyToMany(cascade=CascadeType.MERGE, mappedBy = "foods")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    public Long getFoodId() {
        return this.foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Food(Long foodId, String foodName, Long price, String image, List<Order> orders) {
        super();
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.image = image;
        this.orders = orders;
    }
    
    public Food(String foodName, Long price, String image) {
        super();
        this.foodName = foodName;
        this.price = price;
        this.image = image;
    }

    public Food() {
        super();
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Food)
        {
            sameSame = this.foodName.equals(((Food) object).foodName);
        }

        return sameSame;
    }
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
  @JsonIgnore
    @ManyToMany(cascade=CascadeType.MERGE, mappedBy = "foods")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Branch> branches;

	public List<Branch> getBranches() {
		return branches;
	}
	
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
  
}

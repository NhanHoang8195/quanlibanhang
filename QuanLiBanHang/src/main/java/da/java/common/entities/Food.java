package da.java.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "food")
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
    @Column(name = "food_name", nullable = false)
    private String foodName;

    /** Price of the food*/
    @Column(name = "price", nullable = false)
    private Long price;
    
    /** Many To Many relationship with product_order table*/
    @ManyToMany(mappedBy = "foods", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    public Long getFoodId() {
        return foodId;
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

    public Food(Long foodId, String foodName, Long price, List<Order> orders) {
        super();
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.orders = orders;
    }

    public Food() {
        super();
    }
}

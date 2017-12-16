package da.java.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import da.java.common.enums.OrderStatus;
import da.java.common.enums.TransactionalPerson;

@Entity
@Table(name = "product_order")
@JsonInclude(value = Include.NON_NULL)
public class Order implements Serializable {

    /**
     * The Constant serialVersionUID
     */
    private static final long serialVersionUID = 5542030280978634181L;
    
    /** The Id auto increment*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long productOrderId;
    
    /** Phone number of an customer will receive product*/
    @Column(name = "phone")
    private String phone;
    
    /** Address for receive product*/
    @Column(name = "address")
    private String address;
    
    /** Total money of a bill*/
    @Column(name="total_money")
    private Integer totalMoney;
    
    /** Date done order*/
    @Column(name="date_order")
    private String dateOrder; 
    
    /** Type of order*/
    @Column(name = "transactional_person")
    @Enumerated(EnumType.STRING)
    private TransactionalPerson transactionalPerson;
    
    @ManyToOne
    @JoinColumn(name="branch_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Branch branch;
    
    /**Status orders*/
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    /** List Id of foods*/
    
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(  name = "product_order_food", 
    joinColumns = @JoinColumn(name = "product_order_id"),
    inverseJoinColumns = @JoinColumn(name = "food_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Food> foods;
    
    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(Long productOrderId, String phone, String address, Integer totalMoney, Branch branch,
            OrderStatus orderStatus, List<Food> foods) {
        super();
        this.productOrderId = productOrderId;
        this.phone = phone;
        this.address = address;
        this.totalMoney = totalMoney;
        this.branch = branch;
        this.orderStatus = orderStatus;
        this.foods = foods;
    }

    public Order() {
        super();
    }
}

package da.java.common.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1106770573076957060L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany
    @JoinColumn(name = "category_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Food> foods;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Category(Long categoryId, String name, List<Food> foods) {
        super();
        this.categoryId = categoryId;
        this.name = name;
        this.foods = foods;
    }

    public Category() {
        super();
    }
    
}

package da.java.common.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import da.java.common.entities.Food;

@RepositoryRestResource
public interface FoodRepository extends JpaRepository<Food, Long> {
    /**
     * Get top id (5)
     */
    @Query(nativeQuery=true, value = "SELECT f.food_id FROM food as f, product_order_food as pr WHERE f.food_id = pr.food_id group by f.food_id limit 5")
    public List<BigInteger> findTopFood();
    /**
     * Get list food following top id as above
     * @param ids
     * @return List<Food>
     */
    public List<Food> findAllByFoodIdInOrderByFoodIdDesc(List<Long> ids);
    
    /**
     * Seach food by name ignore case (Upper or lower)
     */
    public List<Food> findByFoodNameContainingIgnoreCase(String name);
    
}

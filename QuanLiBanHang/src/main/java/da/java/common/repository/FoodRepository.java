package da.java.common.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import da.java.common.entities.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query(nativeQuery=true, value = "SELECT f.food_id FROM food as f, product_order_food as pr WHERE f.food_id = pr.food_id group by f.food_id limit 5")
    public List<BigInteger> findTopFood();
    
    public List<Food> findAllByFoodIdInOrderByFoodIdDesc(List<Long> ids);
}

package da.java.common.service;

import java.util.List;

import da.java.common.entities.Food;

public interface FoodService {
    /** Get All Foods*/
    public List<Food> getFoodList();
    
    /** Get top 5 sell*/
    public List<Food> getBestSell();
    
    public List<Food> search(String q);
}

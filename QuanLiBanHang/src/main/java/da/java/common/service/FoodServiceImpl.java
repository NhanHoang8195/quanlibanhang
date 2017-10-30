package da.java.common.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.java.common.entities.Food;
import da.java.common.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;
    
    private static final Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    
    @Override
    public List<Food> getFoodList() {
        try {
            logger.debug("IN - loadFoodList()");
            List<Food> getFoodList = foodRepo.findAll();
            if (getFoodList == null) {
                throw new Exception ("can not get resource");
            }
            return getFoodList;
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
            return null;
        } finally {
            logger.debug("OUT - loadFoodList()");
        }
    }
    
    @Override
    public List<Food> getBestSell() {
        try{
            logger.debug("IN - getBestSell()");
            List<BigInteger> topSell = foodRepo.findTopFood();
            List<Long> top = new ArrayList<>();
            for(BigInteger big : topSell) { // convert BigInt to Long
                top.add(big.longValue());
            }
            
            List<Food> listTopSell = foodRepo.findAllByFoodIdInOrderByFoodIdDesc(top);
            return listTopSell;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        finally {
            logger.debug("OUT - getBestSell()");
        }
    }
    
    public List<Food> search(String q)
    {
    	// NeetToImplement
    	return getBestSell();
    }
}

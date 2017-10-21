package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import da.java.common.entities.Food;
import da.java.common.service.FoodService;


@Controller
public class HomeController {
    
    @Autowired
    private FoodService foodService;
    
	@GetMapping("/")
    public String index(Model model) {
	    List<Food> listFood = foodService.getFoodList();
	    model.addAttribute("listFood", listFood);
        return "home/index";
    }
	@GetMapping("/about")
    public String about(Model model) {
        return "home/about";
    }
}

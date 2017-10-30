package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import da.java.common.entities.Food;
import da.java.common.service.FoodService;



@Controller
@RequestMapping("food")
public class FoodController {
	
	@Autowired
    private FoodService foodService;
	
	@GetMapping("/")
    public String index(Model model) {
		List<Food> listFood = foodService.getFoodList();
		model.addAttribute("listFood", listFood);
        return "food/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        return "food/details";
    }
	
	@GetMapping("/search")
    public String list(@RequestParam String q, Model model) {
		List<Food> listFood = foodService.search(q);
		model.addAttribute("listFood", listFood);
		model.addAttribute("q", q);
        return "food/index";
    }
}

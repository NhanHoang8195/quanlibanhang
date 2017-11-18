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
@RequestMapping("admin")
public class AdminController {
	
	@GetMapping("/index")
    public String index(Model model) {
		return "admin/index";
    }
	
	@GetMapping("/food")
    public String food(Model model) {
		return "admin/food";
    }
	
	@GetMapping("/branch")
    public String branch(Model model) {
		return "admin/branch";
    }
	@GetMapping("/customer")
    public String customer(Model model) {
		return "admin/customer";
    }
}

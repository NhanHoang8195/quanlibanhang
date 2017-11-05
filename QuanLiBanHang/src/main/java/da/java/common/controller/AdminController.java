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
	
	@GetMapping("/")
    public String index(Model model) {
		return "admin/index";
    }
	
}

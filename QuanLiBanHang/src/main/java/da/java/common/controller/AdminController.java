package da.java.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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

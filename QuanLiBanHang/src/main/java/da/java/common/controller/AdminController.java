package da.java.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
	
	@GetMapping({"/admin/index"})
    public String index(Model model) {
		return "admin/index";
    }
	
	@GetMapping("/admin/food")
    public String food(Model model) {
		return "admin/food";
    }
	
	@GetMapping("/admin/branch")
    public String branch(Model model) {
		return "admin/branch";
    }
	@GetMapping("/admin/customer")
    public String customer(Model model) {
		return "admin/customer";
    }
	@GetMapping("/admin/category")
    public String category(Model model) {
		return "admin/category";
    }
	@GetMapping("/admin/orderSwitchboard")
    public String orderSwitchboard(Model model) {
		return "admin/orderSwitchboard";
    }
	@GetMapping("/admin/orderBranch")
    public String orderBranch(Model model) {
		return "admin/orderBranch";
    }
	
}

package da.java.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import da.java.common.entities.Account;
import da.java.common.service.AccountService;


@Controller
public class AdminController {
	
	  @Autowired
	    private AccountService accountService;
	  
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
	@GetMapping("/admin/staff")
    public String staff(Model model) {
		return "admin/staff";
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
	
	@PutMapping("/admin/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> json) {
		String email = json.get("email");
		String password = json.get("password");
		accountService.updatePassword(email, password);
		
		return new ResponseEntity<Account>(HttpStatus.OK);
    }
}

package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import da.java.common.entities.Account;
import da.java.common.entities.Order;
import da.java.common.service.AccountService;


@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/")
    public String index(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();				    
		    Account account = accountService.getAccount(currentUserName);
		    List<Order> listOrder = account.getOrder();
			model.addAttribute("listOrder", listOrder);
		}
		
        return "order/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        return "order/details";
    }
}
package da.java.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import da.java.common.entities.Account;
import da.java.common.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * Use for register account
     */
    @Autowired
    private AccountService accountService;
    
	@GetMapping("/login")
    public String login(Model model) {
	    model.addAttribute("login", new Account());
        return "account/login";
    }
    	
	@GetMapping("/register")
    public String register(Model model) {
	    model.addAttribute("account", new Account());
        return "account/register";
    }

	@PostMapping("/register")
	public String registerAccount(@Valid Account account, BindingResult result, RedirectAttributes redirect, HttpServletRequest req) {
	    if(result.hasErrors()) {
	        FieldError x= result.getFieldError();
	        System.out.println(x);
            return "account/register";
        }
	    boolean temp = accountService.registerAccount(account);
	    if (temp == false) {
	        return "redirect:/account/register?error";
	    }
	    redirect.addFlashAttribute("success", "Tạo tài khoản thành công, vui lòng đăng nhập");
	   return "redirect:/account/login";
	   
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        System.out.println("Logout");
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	@GetMapping("")
	public String account(HttpServletRequest request, HttpServletResponse response) {
		return "account/account";
	}
}

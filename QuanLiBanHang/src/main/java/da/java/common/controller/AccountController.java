package da.java.common.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import da.java.common.entities.Account;
import da.java.common.model.Login;
import da.java.common.service.AccountService;
import da.java.common.service.LoginService;


@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     * Use for loggin account
     */
    @Autowired
    private LoginService loginService;
    
    /**
     * Use for register account
     */
    @Autowired
    private AccountService accountService;
    
	@GetMapping("/login")
    public String login(Model model) {
	    model.addAttribute("login", new Login());
        return "account/login";
    }
	
	@GetMapping("/register")
    public String register(Model model) {
	    model.addAttribute("account", new Account());
        return "account/register";
    }
	
	@PostMapping("/login")
	public String loginAccount(@Valid Login login, BindingResult result, RedirectAttributes redirect) {
	   
	    if(result.hasErrors()) {
	        return "account/login";
	    }
	    Account isSuccess = loginService.isSuccess(login);
	    if(isSuccess == null) {
	        return "redirect:/account/login?error";
	    } 
	    return "redirect:/";
	}
	
	@PostMapping("/register")
	public String registerAccount(@Valid Account account, BindingResult result, RedirectAttributes redirect) {
	    if(result.hasErrors()) {
	        FieldError x= result.getFieldError();
	        System.out.println(x);
            return "account/register";
        }
	    Account temp = accountService.registerAccount(account);
	    if(temp == null) {
	        return "redirect:/account/register?error";
	    }
	    redirect.addFlashAttribute("success", "Tạo tài khoản thành công");
	    return "redirect:/";
	}
}

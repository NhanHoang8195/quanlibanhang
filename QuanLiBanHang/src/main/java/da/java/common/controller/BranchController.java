package da.java.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("branch")
public class BranchController {
	
	@GetMapping("/")
    public String index(Model model) {
        return "branch/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        return "branch/details";
    }
}

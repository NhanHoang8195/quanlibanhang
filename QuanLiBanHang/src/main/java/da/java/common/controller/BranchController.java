package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import da.java.common.entities.Branch;
import da.java.common.repository.BranchRepository;

@Controller
@RequestMapping("branch")
public class BranchController {
	 @Autowired
	    BranchRepository branchRepository;
	 
	@GetMapping("/")
    public String index(Model model) {
		 List<Branch> branchs = branchRepository.findAll();
		 model.addAttribute("branchs", branchs);
        return "branch/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        return "branch/details";
    }
}

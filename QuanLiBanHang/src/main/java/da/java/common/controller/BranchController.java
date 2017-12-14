package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import da.java.common.entities.Branch;
import da.java.common.entities.Food;
import da.java.common.repository.BranchRepository;
import da.java.common.service.BranchService;

@Controller
@RequestMapping("branch")
public class BranchController {
	 @Autowired
	    BranchRepository branchRepository;
	 
	 @Autowired
	 private BranchService branchService;
	 
	@GetMapping("/")
    public String index(Model model) {
		 List<Branch> branchs = branchRepository.findAll();
		 model.addAttribute("branchs", branchs);
        return "branch/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
		 Branch branch = branchService.getBranch((long)id);
		 model.addAttribute("branch", branch);
		 
		 List<Food> listFood = branch.getFoods();
		 model.addAttribute("listFood", listFood);
        return "branch/details";
    }
	
	@PostMapping
	public ResponseEntity<Branch> postBranch(@RequestBody Branch branch) {
	    Branch result = branchRepository.save(branch);
	    return new ResponseEntity<Branch> (result, HttpStatus.OK);
	    
	}
}

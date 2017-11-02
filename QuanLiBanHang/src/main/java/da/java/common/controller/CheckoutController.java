package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import da.java.common.entities.Food;
import da.java.common.service.FoodService;

@Controller
@RequestMapping("checkout")
public class CheckoutController {

	@GetMapping("/")
    public String index(Model model) {
        return "checkout/index";
    }
}

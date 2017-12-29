package da.java.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import da.java.common.entities.Order;
import da.java.common.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
    public String index(Model model) {
		List<Order> listOrder = orderService.getOrderList();
		//System.out.println(listOrder.get(0).getTotalMoney());
		model.addAttribute("listOrder", listOrder);
        return "order/index";
    }
	
	@GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        return "order/details";
    }
}
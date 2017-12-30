package da.java.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;


@Controller
@RequestMapping("report")
public class ReportController {
	 	@Autowired
	    private DataSource dbsoruce;
	    @Autowired
	    private ApplicationContext appContext;
	    
	    public JasperReportsPdfView View(String name) {
	    	JasperReportsPdfView view = new JasperReportsPdfView();
	        view.setUrl("classpath:reports/" + name + ".jrxml");
	        view.setReportDataKey("datasource");
	        view.setApplicationContext(appContext);
	        
	        return view;
	    }
	    
	    // http://localhost:8080/report/revenueAll
	    @RequestMapping(path = "/revenueAll", method = RequestMethod.GET)
	    public ModelAndView revenueAll() {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        return new ModelAndView(View("RevenueAll"), params);
	    }
	    
	    // http://localhost:8080/report/revenueBranch?branchId=1&branchName=olala
	    @RequestMapping(path = "/revenueBranch", method = RequestMethod.GET)
	    public ModelAndView revenueBranch(@RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        params.put("branchId", branchId);
	        params.put("branchName", branchName);
	        
	        return new ModelAndView(View("RevenueBranch"), params);
	    }
	    
	    // http://localhost:8080/report/orderBranch?branchId=1&branchName=olala
	    @RequestMapping(path = "/orderBranch", method = RequestMethod.GET)
	    public ModelAndView orderBranch(@RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        params.put("branchId", branchId);
	        params.put("branchName", branchName);
	        
	        return new ModelAndView(View("OrderBranch"), params);
	    }
	    
	    // http://localhost:8080/report/foodAll
	    @RequestMapping(path = "/foodAll", method = RequestMethod.GET)
	    public ModelAndView foodAll() {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        return new ModelAndView(View("FoodAll"), params);
	    }
	    
	    // http://localhost:8080/report/foodBranch?branchId=1&branchName=olala
	    @RequestMapping(path = "/foodBranch", method = RequestMethod.GET)
	    public ModelAndView foodBranch(@RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        params.put("branchId", branchId);
	        params.put("branchName", branchName);
	        
	        return new ModelAndView(View("FoodBranch"), params);
	    }
	    
	    // http://localhost:8080/report/orderCustomer?accountId=3&accountName=olala
	    @RequestMapping(path = "/orderCustomer", method = RequestMethod.GET)
	    public ModelAndView orderCustomer(@RequestParam("accountId") Integer accountId, @RequestParam("accountName") String accountName) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("datasource", dbsoruce);
	        
	        params.put("accountId", accountId);
	        params.put("accountName", accountName);
	        
	        return new ModelAndView(View("OrderCustomer"), params);
	    }
}

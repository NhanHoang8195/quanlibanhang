package da.java.common;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import da.java.common.entities.Account;
import da.java.common.entities.Branch;
import da.java.common.entities.Food;
import da.java.common.entities.Role;
import da.java.common.enums.RoleName;
import da.java.common.repository.AccountRepository;
import da.java.common.repository.BranchRepository;
import da.java.common.repository.FoodRepository;
import da.java.common.repository.RoleRepository;

@SpringBootApplication
public class QuanLiBanHangApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(QuanLiBanHangApplication.class, args);
	}
	@EventListener({ApplicationReadyEvent.class})
	void applicationReadyEvent() throws UnknownHostException {
	    System.out.println("Application started ... launching browser now");
	   Browse("http://localhost:8080/");
	}

	public static void Browse(String url) {
	    if(Desktop.isDesktopSupported()){
	        Desktop desktop = Desktop.getDesktop();
	        try {
	            desktop.browse(new URI(url));
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	        }
	    }else{
	        Runtime runtime = Runtime.getRuntime();
	        try {
	            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	 @Autowired
		private RoleRepository roleRepository;
	 @Autowired
		private FoodRepository foodRepository;
	 @Autowired
		private BranchRepository branchRepository;
	 
	@Override
    public void run(String... arg0) throws Exception {
		DataInitializer dataInitializer = new DataInitializer();
		dataInitializer.initFood(foodRepository);
		dataInitializer.initBranch(branchRepository);
		dataInitializer.initRole(roleRepository);
    }
}

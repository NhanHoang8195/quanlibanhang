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

import da.java.common.entities.Food;
import da.java.common.repository.FoodRepository;

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

	@Override
    public void run(String... arg0) throws Exception {
		initDataForFoodTable();
    }
	
	@Autowired
    FoodRepository foodRepository;
	 @Transactional
	 public void initDataForFoodTable()
	{
		 List<Food> foods = foodRepository.findAll();
		 
		 Food food1 = new Food("Mì sốt bò xay",(long)70000, "https://media.foody.vn/res/g1/8921/s570x570/20177149407-mi-bo-xay.jpg");
		 if(!foods.contains(food1))
		 {
			 foodRepository.save(food1);
		 }
		 
		 Food food2 = new Food("Cơm rang gà quay",(long)80000, "https://media.foody.vn/res/g2/13882/s570x570/foody-c%C6%A1m%20rang%20g%C3%A0%20quay-635925295629704502.jpg");
		 if(!foods.contains(food2))
		 {
			 foodRepository.save(food2);
		 }
		 
		 Food food3 = new Food("Cơm Bò Gạo Lứt",(long)75000, "https://media.foody.vn/res/g70/692705/s570x570/2017108225131-com-bo-gao-lut.jpg");
		 if(!foods.contains(food3))
		 {
			 foodRepository.save(food3);
		 }
		 
		 Food food4 = new Food("Salad Gà Trứng",(long)76000, "https://media.foody.vn/res/g70/692705/s570x570/2017108125914-salad-ga-trung.jpg");
		 if(!foods.contains(food4))
		 {
			 foodRepository.save(food4);
		 }
		 
		 Food food5 = new Food("Cơm rang thập cẩm",(long)77000, "https://media.foody.vn/res/g2/13882/s570x570/2016817144416-com-rang-thap-cam.jpg");
		 if(!foods.contains(food5))
		 {
			 foodRepository.save(food5);
		 }
		 
		 Food food6 = new Food("Mì xào gà",(long)78000, "https://media.foody.vn/res/g2/13882/s570x570/2016817144525-mi-xao-ga.jpg");
		 if(!foods.contains(food6))
		 {
			 foodRepository.save(food6);
		 }
	}
	 
	 
}

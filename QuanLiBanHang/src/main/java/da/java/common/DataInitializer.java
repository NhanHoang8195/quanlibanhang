package da.java.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import da.java.common.entities.Account;
import da.java.common.entities.Branch;
import da.java.common.entities.Food;
import da.java.common.entities.Role;
import da.java.common.enums.RoleName;
import da.java.common.repository.AccountRepository;
import da.java.common.repository.BranchRepository;
import da.java.common.repository.FoodRepository;
import da.java.common.repository.RoleRepository;

public class DataInitializer {
    
	 public void initBranch(BranchRepository branchRepository)
	 {
		 List<Branch> branchs = branchRepository.findAll();
		 
		 // https://www.foody.vn/ha-noi/la-salsa-bui-thi-xuan/goi-mon
		 Branch branch1 = new Branch("La Salsa","09670324001", "5 Bùi Thị Xuân, Quận Hai Bà Trưng", "https://media.foody.vn/res/g14/136680/prof/s480x300/foody-mobile-la-salsa1-jpg-316-635675632346110249.jpg");
		 if(!branchs.contains(branch1))
		 {
			 branchRepository.save(branch1);
		 }
		 
		 // https://www.foody.vn/ha-noi/nam-son-cuisine-tiec-cuoi-hoi-nghi?isnulldeli=1
		 Branch branch2 = new Branch("Nam Sơn Cuisine","09670324002", "809 Giải Phóng, Quận Hoàng Mai", "https://media.foody.vn/res/g2/15429/prof/s640x400/foody-mobile-nam-son-jpg-390-635786845267621697.jpg");
		 if(!branchs.contains(branch2))
		 {
			 branchRepository.save(branch2);
		 }
		 
		 // https://www.foody.vn/ha-noi/goc-quan-am-thuc-viet
		 Branch branch3 = new Branch("Gộc Quán","09670324003", "6B Đường Thành,  Quận Hoàn Kiếm", "https://media.foody.vn/res/g10/94782/prof/s640x400/foody-mobile-moc-jpg-271-635814523748648630.jpg");
		 if(!branchs.contains(branch3))
		 {
			 branchRepository.save(branch3);
		 }
		 
		// https://www.foody.vn/ha-noi/nam-son-cuisine-tiec-cuoi-hoi-nghi?isnulldeli=1
		 Branch branch4 = new Branch("Khazaana 1992","09670324004", "34 Đường Thành,  Quận Hoàn Kiếm", "https://media.foody.vn/res/g11/103383/prof/s640x400/foody-mobile-4-jpg-168-636341545102663516.jpg");
		 if(!branchs.contains(branch4))
		 {
			 branchRepository.save(branch4);
		 }
	 }
	 public void initFood(FoodRepository foodRepository)
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
	 public void initRole(RoleRepository roleRepository)
	{
		 List<Role> roles = roleRepository.findAll();
		 
		 Role role1 = new Role(RoleName.ROLE_ADMIN);
		 if(!roles.contains(role1))
		 {
			 roleRepository.save(role1);
		 }
		 Role role2 = new Role(RoleName.ROLE_MEMBER);
		 if(!roles.contains(role2))
		 {
			 roleRepository.save(role2);
		 }
		 Role role3 = new Role(RoleName.ROLE_SWITCHBOARD);
		 if(!roles.contains(role3))
		 {
			 roleRepository.save(role3);
		 }
		 Role role4 = new Role(RoleName.ROLE_BRANCH);
		 if(!roles.contains(role4))
		 {
			 roleRepository.save(role4);
		 }
	}
	 
	 public void initAdminAccount(AccountRepository accountRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
	     if(accountRepo.findByEmail("admin@gmail.com") == null) {
	         Account account = new Account();
	         Set<Role> roles = new HashSet<>();
	         roles.add(roleRepo.findByRoleName(RoleName.ROLE_ADMIN));
	         account.setEmail("admin@gmail.com");
	         account.setRoles(roles);
	         account.setPassword(passwordEncoder.encode("123456"));
	         account.setAddress("None");
	         account.setRealName("NhanHoang");
	         account.setPhone("0968005379");
	         accountRepo.save(account);	     
	     }
	     if(accountRepo.findByEmail("switchboard@gmail.com") == null) {
	         Account account = new Account();
	         Set<Role> roles = new HashSet<>();
	         roles.add(roleRepo.findByRoleName(RoleName.ROLE_SWITCHBOARD));
	         account.setEmail("switchboard@gmail.com");
	         account.setRoles(roles);
	         account.setPassword(passwordEncoder.encode("123456"));
	         account.setAddress("None");
	         account.setRealName("Tong dai 1");
	         account.setPhone("0968005399");
	         accountRepo.save(account);
	     }
	     if(accountRepo.findByEmail("branch@gmail.com") == null) {
	         Account account = new Account();
	         Set<Role> roles = new HashSet<>();
	         roles.add(roleRepo.findByRoleName(RoleName.ROLE_BRANCH));
	         account.setEmail("branch@gmail.com");
	         account.setRoles(roles);
	         account.setPassword(passwordEncoder.encode("123456"));
	         account.setAddress("None");
	         account.setRealName("Quan ly chi nhanh 1");
	         account.setPhone("0968005388");
	         accountRepo.save(account);
	     }
	 }
}

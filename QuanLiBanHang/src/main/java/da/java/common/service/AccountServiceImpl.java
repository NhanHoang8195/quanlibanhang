package da.java.common.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import da.java.common.entities.Account;
import da.java.common.entities.Role;
import da.java.common.enums.RoleName;
import da.java.common.repository.AccountRepository;
import da.java.common.repository.RoleRepository;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Override
    public boolean registerAccount(Account account) {
        try{
            Account temp = accountRepo.findByEmail(account.getEmail());
            if(temp != null) {  // exists this email
                throw new Exception();
            }
            Set<Role> roles = new HashSet<>();
            
            roles.add(roleRepo.findByRoleName(RoleName.ROLE_MEMBER));
            account.setRoles(roles);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            account =  accountRepo.save(account);
            if(account == null) {
                throw new Exception();
            }
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    @Override
    public void updatePassword(String email, String password) {
        try{
            Account account = accountRepo.findByEmail(email);
    
            account.setPassword(passwordEncoder.encode(password));
            account =  accountRepo.save(account);
            if(account == null) {
                throw new Exception();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Account getAccount(String email) {
        try{
            Account account = accountRepo.findByEmail(email);
            return account;
         
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return null;
    }
    @Override
    public boolean updateAccount(Account account) {
        try{
            Account temp = accountRepo.findByEmail(account.getEmail());
    
            temp.setAddress(account.getAddress());
            temp.setPhone(account.getPhone());
            temp.setRealName(account.getRealName());
            temp =  accountRepo.save(temp);
            if(temp == null) {
                throw new Exception();
            }
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

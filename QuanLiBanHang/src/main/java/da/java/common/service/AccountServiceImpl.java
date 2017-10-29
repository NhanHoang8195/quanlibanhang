package da.java.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.java.common.entities.Account;
import da.java.common.entities.Role;
import da.java.common.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepo;
    
    @Override
    public Account registerAccount(Account account) {
        try{
            Account temp = accountRepo.findByEmail(account.getEmail());
            if(temp != null) {  // exists this email
                return null;
            }
            Role role = new Role();
            role.setRoleId((long) 1);
            account.setRoleId(role);
            account = accountRepo.save(account);
            if(account == null) {
                return null;
            }
            return account;
        }catch (Exception e) {
            return null;
        }
        
    }

}

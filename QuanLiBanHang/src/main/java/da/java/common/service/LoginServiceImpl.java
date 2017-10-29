package da.java.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import da.java.common.entities.Account;
import da.java.common.model.Login;
import da.java.common.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepo;
    
    @Override
    public Account isSuccess(Login account) {
        try {
            Account isSuccessLogin = loginRepo.findByEmail(account.getEmail());
            if(isSuccessLogin == null) {
                return null;
            } else {
                if(isSuccessLogin.getPassword().equals(account.getPassword())) {
                    return isSuccessLogin;
                }
                return null;
            }
        } catch (Exception e){
            return null;
        }
        
    }

}

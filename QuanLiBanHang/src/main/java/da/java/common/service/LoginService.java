package da.java.common.service;

import da.java.common.entities.Account;
import da.java.common.model.Login;

public interface LoginService {
    public Account isSuccess(Login account);
}

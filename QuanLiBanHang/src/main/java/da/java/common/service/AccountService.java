package da.java.common.service;

import da.java.common.entities.Account;

public interface AccountService {
    public boolean registerAccount(Account account);
    public void updatePassword(String email, String password);
}

package com.debanshu.practice.services;

import com.debanshu.practice.models.Account;
import java.util.List;

public interface AccountService {

    public boolean checkEmail(String email);
    
    public Account register(Account account);
    
    public Account login(String email, String password);
    
    public void delete(Long id);
    
    public List<Account> getAll();
}

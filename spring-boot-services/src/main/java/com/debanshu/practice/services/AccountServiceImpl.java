package com.debanshu.practice.services;

import com.debanshu.practice.models.Account;
import com.debanshu.practice.models.AccountDAO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO dao;

    @Override
    public Account register(Account account) {
        return dao.save(account);
    }

    @Override
    public Account login(String email, String password) {
        List<Account> accounts = getAll();
        if (accounts != null && !accounts.isEmpty()) {
            Optional<Account> optional = accounts
                    .stream()
                    .filter(a -> a.getEmail().equals(email) && a.getPassword().equals(password))
                    .findFirst();
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Account> getAll() {
        return StreamSupport.stream(
                dao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkEmail(String email) {
        List<Account> accounts = getAll();
        return accounts.stream().anyMatch(a -> a.getEmail().equals(email));
    }

}

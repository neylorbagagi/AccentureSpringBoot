package com.accenture.academico.service;

import com.accenture.academico.model.Account;
import com.accenture.academico.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public List<Account> getAllAccounts(){
        List<Account> accountList = new ArrayList<Account>();
        repository.findAll().forEach(account -> accountList.add(account));
        return accountList;
    }

    public Account getAccountById(int id) {
        return repository.findById(id).get();
    }

    public Account saveOrUpdateAccount(Account account){
        return repository.save(account);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}

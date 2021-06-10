package com.accenture.academico.controller;

import com.accenture.academico.model.Account;
import com.accenture.academico.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping("/accounts")
    public List<Account> getAllAccount(){
        return service.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    private Account getAccount(@PathVariable("id") int id) {
        return service.getAccountById(id);
    }

    @DeleteMapping("/account/{id}")
    private void deleteAccount(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping("/account")
    public Account saveAccount(@RequestBody Account account){
        return service.saveOrUpdateAccount(account);
    }

}

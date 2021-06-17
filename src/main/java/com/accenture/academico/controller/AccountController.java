package com.accenture.academico.controller;

import com.accenture.academico.exception.RegisterNotFoundException;
import com.accenture.academico.model.Account;
import com.accenture.academico.model.Client;
import com.accenture.academico.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private ResponseEntity<Account> getAccount(@PathVariable("id") int id) throws RegisterNotFoundException {
        Account account;
        try{
            account = service.getAccountById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    private void deleteAccount(@PathVariable("id") int id) throws RegisterNotFoundException {
        try{
            service.delete(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }
    }

    @PostMapping("/account")
    public Account saveAccount(@RequestBody Account account, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.saveOrUpdateAccount(account);
    }

}

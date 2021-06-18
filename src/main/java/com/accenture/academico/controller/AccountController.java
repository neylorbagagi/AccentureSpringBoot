package com.accenture.academico.controller;


import com.accenture.academico.exception.RegisterNotFoundException;
import com.accenture.academico.model.Account;
import com.accenture.academico.model.Statement;
import com.accenture.academico.model.StatementOperation;
import com.accenture.academico.service.AccountService;
import com.accenture.academico.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
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
    public ResponseEntity<Account> getAccount(@PathVariable("id") int id) throws RegisterNotFoundException {
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
    public Account saveAccount(@Valid @RequestBody Account account, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.saveOrUpdateAccount(account);
    }

    @PutMapping("/account/{id}/deposit/{value}")
    public Account depositInAccount(@Valid @PathVariable("id") int id, @PathVariable("value") double value, HttpServletResponse response) throws RegisterNotFoundException  {

        Account account;
        try{
            account = service.getAccountById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return service.depositValueToAccountById(value,id);
    }

    @PutMapping("/account/{id}/withdraw/{value}")
    public Account withdrawFromAccount(@Valid @PathVariable("id") int id, @PathVariable("value") double value, HttpServletResponse response) throws RegisterNotFoundException  {

        Account account;
        try{
            account = service.getAccountById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return service.withdrawValueToAccountById(value,id);
    }

    @GetMapping("/account/{id}/statements")
    private List<Statement> getAccountStatements(@PathVariable("id") int id) throws RegisterNotFoundException {
        Account account;
        try{
            account = service.getAccountById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return account.getStatements();
    }

    @PutMapping("/account/{originId}/transfer/{value}/{destinyId}")
    public Account transferFromAccount(@Valid @PathVariable("originId") int originId,
                                              @PathVariable("destinyId") int destinyId,
                                              @PathVariable("value") double value,
                                       HttpServletResponse response) throws RegisterNotFoundException  {

        Account originAccount;
        Account destinyAccount;
        try{
            originAccount = service.getAccountById(originId);
            destinyAccount = service.getAccountById(destinyId);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return service.transferValueToAccountById(value,originId,destinyId);
    }

}

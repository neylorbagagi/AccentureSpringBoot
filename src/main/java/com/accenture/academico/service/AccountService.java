package com.accenture.academico.service;

import com.accenture.academico.model.Account;
import com.accenture.academico.model.Statement;
import com.accenture.academico.model.StatementOperation;
import com.accenture.academico.repository.AccountRepository;
import com.accenture.academico.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private StatementRepository statementRepository;

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

    public int delete(int id) {
        repository.deleteById(id);
        return id;
    }

    public Account depositValueToAccountById(double value,int id){
        Account account = this.getAccountById(id);
        account.deposit(value);

        Statement statement = new Statement(value,new Date(), StatementOperation.DEPOSIT,account);
        statementRepository.save(statement);

        return this.saveOrUpdateAccount(account);
    }

    public Account withdrawValueToAccountById(double value,int id){
        Account account = this.getAccountById(id);
        account.withdraw(value);

        Statement statement = new Statement(value,new Date(), StatementOperation.WITHDRAWAL,account);
        statementRepository.save(statement);

        return this.saveOrUpdateAccount(account);
    }

    public Account transferValueToAccountById(double value,int originId, int destinyId){
        Account originAccount = this.getAccountById(originId);
        originAccount.withdraw(value);

        Account destinyAccount = this.getAccountById(destinyId);
        destinyAccount.deposit(value);

        Date transactionDate = new Date();
        //Statement withdrawStatement = new Statement(value,transactionDate, StatementOperation.WITHDRAWAL,originAccount);
        Statement transferStatement = new Statement(-value,transactionDate, StatementOperation.TRANSFER,originAccount);
        Statement depositStatement = new Statement(value,transactionDate, StatementOperation.DEPOSIT,destinyAccount);

        //statementRepository.save(withdrawStatement);
        statementRepository.save(transferStatement);
        statementRepository.save(depositStatement);

        this.saveOrUpdateAccount(destinyAccount);

        return this.saveOrUpdateAccount(originAccount);
    }

}

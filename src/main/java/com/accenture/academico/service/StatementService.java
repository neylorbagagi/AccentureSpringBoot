package com.accenture.academico.service;

import com.accenture.academico.model.Statement;
import com.accenture.academico.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    StatementRepository repository;

    public List<Statement> getAllStatements(){
        List<Statement> statementList = new ArrayList<Statement>();
        repository.findAll().forEach(statement -> statementList.add(statement));
        return statementList;
    }

    public Statement getStatementById(int id) {
        return repository.findById(id).get();
    }

    public Statement saveOrUpdateStatement(Statement statement){
        statement.setDate(new Date());
        return repository.save(statement);
    }

    public int delete(int id) {
        repository.deleteById(id);
        return id;
    }
    
}

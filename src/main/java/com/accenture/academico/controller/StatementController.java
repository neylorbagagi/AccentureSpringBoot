package com.accenture.academico.controller;

import com.accenture.academico.model.Statement;
import com.accenture.academico.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatementController {

    @Autowired
    StatementService service;

    @GetMapping("/statements")
    public List<Statement> getAllStatement(){
        return service.getAllStatements();
    }

    @GetMapping("/statement/{id}")
    private Statement getStatement(@PathVariable("id") int id) {
        return service.getStatementById(id);
    }

    @DeleteMapping("/statement/{id}")
    private void deleteStatement(@PathVariable("id") int id) {
        service.delete(id);
    }
    
    @PostMapping("/statement")
    public Statement saveStatement(@RequestBody Statement statement){
        return service.saveOrUpdateStatement(statement);
    }

}

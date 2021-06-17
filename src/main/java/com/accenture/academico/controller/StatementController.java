package com.accenture.academico.controller;

import com.accenture.academico.exception.RegisterNotFoundException;
import com.accenture.academico.model.Statement;
import com.accenture.academico.model.Statement;
import com.accenture.academico.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private ResponseEntity<Statement> getStatement(@PathVariable("id") int id) throws RegisterNotFoundException {
        Statement statement;
        try{
            statement = service.getStatementById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return new ResponseEntity<Statement>(statement, HttpStatus.OK);
    }

    @DeleteMapping("/statement/{id}")
    private void deleteStatement(@PathVariable("id") int id) throws RegisterNotFoundException {
        try{
            service.delete(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }
    }
    
    @PostMapping("/statement")
    public Statement saveStatement(@RequestBody Statement statement, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.saveOrUpdateStatement(statement);
    }

}

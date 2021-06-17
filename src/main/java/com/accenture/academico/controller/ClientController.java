package com.accenture.academico.controller;

import com.accenture.academico.exception.RegisterNotFoundException;
import com.accenture.academico.model.Client;
import com.accenture.academico.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping("/clients")
    public List<Client> getAllClient(){
        return service.getAllClients();
    }

    @GetMapping("/client/{id}")
    private ResponseEntity<Client> getClient(@PathVariable("id") int id) throws RegisterNotFoundException {
        Client client;
        try{
            client = service.getClientById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    private void deleteClient(@PathVariable("id") int id) throws RegisterNotFoundException {
        try{
            service.delete(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }
    }

    @PostMapping("/client")
    public Client saveClient(@Valid @RequestBody Client client, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.saveOrUpdateClient(client);
    }

}

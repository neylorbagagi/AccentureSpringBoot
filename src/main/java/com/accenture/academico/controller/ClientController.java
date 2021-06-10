package com.accenture.academico.controller;

import com.accenture.academico.model.Client;
import com.accenture.academico.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private Client getClient(@PathVariable("id") int id) {
        return service.getClientById(id);
    }

    @DeleteMapping("/client/{id}")
    private void deleteClient(@PathVariable("id") int id) {
        service.delete(id);
    }
    
    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client){
        return service.saveOrUpdateClient(client);
    }

}

package com.accenture.academico.controller;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return service.getAllClientes();
    }

    @GetMapping("/cliente/{id}")
    private Cliente getCliente(@PathVariable("id") int id) {
        return service.getClienteById(id);
    }

    @DeleteMapping("/cliente/{id}")
    private void deleteCliente(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping("/cliente")
    private int saveCliente(@RequestBody Cliente cliente){
        service.saveOrUpdate(cliente);
        return cliente.getId();
    }

}

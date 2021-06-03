package com.accenture.academico.controller;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping("/clients")
    public List<Cliente> getClientes() {
        return service.getAllClientes();
    }

}

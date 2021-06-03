package com.accenture.academico.service;

import com.accenture.academico.model.Cliente;
import com.accenture.academico.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    ClienteRepository repository;

    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        repository.findAll().forEach(cliente -> clientes.add((Cliente) cliente));
        return clientes;
    }
    
}

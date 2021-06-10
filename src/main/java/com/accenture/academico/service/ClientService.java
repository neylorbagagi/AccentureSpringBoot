package com.accenture.academico.service;

import com.accenture.academico.model.Client;
import com.accenture.academico.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<Client> getAllClients(){
        List<Client> clientList = new ArrayList<Client>();
        repository.findAll().forEach(client -> clientList.add(client));
        return clientList;
    }

    public Client getClientById(int id) {
        return repository.findById(id).get();
    }
    
    public Client saveOrUpdateClient(Client client){
        return repository.save(client);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}

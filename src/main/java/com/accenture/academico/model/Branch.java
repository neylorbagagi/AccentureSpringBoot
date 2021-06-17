package com.accenture.academico.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Campo name não pode ser nulo")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Campo address não pode ser nulo")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "Campo fone não pode ser nulo")
    private String fone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
    @JsonManagedReference
    private List<Account> accounts;

    public Branch() {
    }

    public Branch(String name, String address, String fone, List<Account> accounts) {
        this.name = name;
        this.address = address;
        this.fone = fone;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

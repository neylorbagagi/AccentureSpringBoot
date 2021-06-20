package com.accenture.academico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    @NotNull(message = "Campo number não pode ser nulo")
    private String number;

    @Column(nullable = false)
    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true, nullable = false)
    @JsonManagedReference
    @NotNull(message = "Campo objeto client não pode ser nulo")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonManagedReference
    private List<Statement> statements;

    //@NotNull(message = "Campo branch {id} não pode ser nulo")
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Branch branch;

    public Account() {
    }

    public Account(String number, double balance, Client client, List<Statement> statements, Branch branch) {
        this.number = number;
        this.balance = 0.0;
        this.client = client;
        this.statements = statements;
        this.branch = branch;
    }

    public void deposit(double value) {
        this.balance += value;
    }

    public void withdraw(double value) {
        this.balance -= value;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

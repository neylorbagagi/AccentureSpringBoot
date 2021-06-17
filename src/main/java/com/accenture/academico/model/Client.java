package com.accenture.academico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Campo name não pode ser nulo")
    private String name;

    @Column(nullable = false)
    @Size(max = 14, message = "Campo cpf deve conter no máximo 14 characteres")
    @NotBlank(message = "Campo cpf não pode ser nulo")
    @CPF(message = "Campo cpf inválido")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "Campo fone não pode ser nulo")
    private String fone;

    @OneToOne(mappedBy = "client")
    @JoinColumn(unique = true)
    @JsonBackReference
    private Account account;

    public Client() {}

    public Client(String name, String cpf, String fone, Account account) {
        this.name = name;
        this.cpf = cpf;
        this.fone = fone;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}

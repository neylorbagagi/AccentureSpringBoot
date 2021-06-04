package com.accenture.academico.model;

import javax.persistence.*;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String fone;

    @Column(nullable = false)
    private int contaCorrenteId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public int getContaCorrenteId() {
        return contaCorrenteId;
    }

    public void setContaCorrenteId(int contaCorrenteId) {
        this.contaCorrenteId = contaCorrenteId;
    }
}

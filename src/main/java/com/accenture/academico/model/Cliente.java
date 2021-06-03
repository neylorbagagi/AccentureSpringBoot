package com.accenture.academico.model;

import javax.persistence.*;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String CPF;

    @Column
    private String fone;

    @Column
    private Integer ContaCorrenteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Integer getContaCorrenteId() {
        return ContaCorrenteId;
    }

    public void setContaCorrenteId(Integer contaCorrenteId) {
        ContaCorrenteId = contaCorrenteId;
    }
}

package com.accenture.academico.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_CONTA_CORRENTE")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false)
    private int clienteId;

    @Column(nullable = false)
    private int agenciaId;

}

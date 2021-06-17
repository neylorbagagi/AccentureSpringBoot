package com.accenture.academico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Campo value não pode ser nulo")
    private double value;

    @Column(nullable = false)
    @NotBlank(message = "Campo date não pode ser nulo")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Campo operation não pode ser nulo")
    private StatementOperation operation;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Account account;

    public Statement() {
    }

    public Statement(double value, Date date, StatementOperation operation, Account account) {
        this.value = value;
        this.date = date;
        this.operation = operation;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StatementOperation getOperation() {
        return operation;
    }

    public void setOperation(StatementOperation operation) {
        this.operation = operation;
    }
}

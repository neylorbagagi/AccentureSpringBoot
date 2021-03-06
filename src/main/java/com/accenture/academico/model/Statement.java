package com.accenture.academico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull(message = "Campo value não pode ser nulo")
    private double value;

    //@NotNull(message = "Campo date não pode ser nulo")
    //@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo operation não pode ser nulo")
    private StatementOperation operation;


//    @NotNull(message = "Campo account {id} não pode ser nulo")
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

    public Statement(double value, StatementOperation operation, Account account) {
        new Statement(value,new Date(),operation,account);
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

package com.tcs.RBRBankSpring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String type;
    private Long idClient;
    private Long idAddressee;
    private String description;

    public Transaction() {
    }

    public Transaction(Date date, String type, Long idClient, String description) {
        this.date = date;
        this.type = type;
        this.idClient = idClient;
        this.description = description;
    }

    public Transaction(Long id, Date date, String type, Long idClient, Long idAddressee, String description) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.idClient = idClient;
        this.idAddressee = idAddressee;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdAddressee() {
        return idAddressee;
    }

    public void setIdAddressee(Long idAddressee) {
        this.idAddressee = idAddressee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package ru.sergentum.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "payee_id")
    private Payee payee;

    @NotNull
    private Integer amount;

    @NotEmpty
    private String invoice;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Payee getPayee() {
        return payee;
    }

    public Integer getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + (user != null ? user : "null") +
                ", payee=" + (payee != null ? payee : "null")  +
                ", invoice=" + invoice +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

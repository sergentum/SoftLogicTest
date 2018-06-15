package ru.sergentum.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payee_id")
    private Payee payee;

    private int amount;

    @NotEmpty
    private String invoice;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    public Date timestamp;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Payee getPayee() {
        return payee;
    }

    public int getAmount() {
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
                ", user=" + user.getUsername() +
                ", payee=" + payee.getName() +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

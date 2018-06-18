package ru.sergentum.service;

import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionList(User user);

    void save(Transaction transaction);
}

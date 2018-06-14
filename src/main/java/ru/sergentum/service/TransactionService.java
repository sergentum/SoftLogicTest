package ru.sergentum.service;

import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;

import java.util.List;

public interface TransactionService {

    Transaction doTransaction(String userName, String payeeName, Integer amount);

    List<Transaction> getTransactionList(User user);
}

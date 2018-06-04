package ru.sergentum.service;

import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;

import java.util.List;

public interface TransactionService {

    boolean doTransaction(User user, Payee payee, Integer amount);

    List<Transaction> getTransactionList(User user);
}

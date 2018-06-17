package ru.sergentum.repository.jpa;

import ru.sergentum.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAll(Integer userId);
    boolean save(Transaction transaction);
    void deleteAll();
}

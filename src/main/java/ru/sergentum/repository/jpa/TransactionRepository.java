package ru.sergentum.repository.jpa;

import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAll(Integer userId);
    boolean save(Transaction transaction);

    List<Transaction> getAllByTimestampBetween(Date startDate, Date endDate);

    List<Transaction> getAllByAmountBetween(Integer minAmount, Integer maxAmount);

    List<Transaction> getAllByPayeeIs(Payee payee);
}

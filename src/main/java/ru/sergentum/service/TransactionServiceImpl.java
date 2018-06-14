package ru.sergentum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction doTransaction(User user, Payee payee, Integer amount) {


        Transaction transaction = new Transaction();

        transaction.setUser(user);

        transaction.setPayee(payee);

        transaction.setAmount(amount);

        user.setBalance(user.getBalance() - amount);

        return null;
    }

    @Override
    public List<Transaction> getTransactionList(User user) {
        return null;
    }
}

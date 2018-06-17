package ru.sergentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergentum.controller.TransactionController;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
import ru.sergentum.repository.jpa.TransactionRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        logger.info("Got transaction to check and save: {}", transaction);
        transaction.setTimestamp(new Date());
        User user = transaction.getUser();
        Integer userBalance = user.getBalance();
        String userName = user.getUsername();

        if (userBalance < transaction.getAmount()) {
            throw new IllegalStateException("Insufficient funds");
        } else if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        }

        userService.changeBalance(userName, -transaction.getAmount());
        transactionRepository.save(transaction);
        logger.info("Transaction saved: {}", transaction);
    }

    @Override
    public List<Transaction> getTransactionList(User user) {

        return transactionRepository.getAll(user.getId());
    }
}

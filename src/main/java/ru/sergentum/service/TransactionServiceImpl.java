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

    private PayeeRepository payeeRepository;

    private Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService, PayeeRepository payeeRepository) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.payeeRepository = payeeRepository;
    }

    @Override
    @Transactional
    public Transaction doTransaction(String userName, String payeeName, Integer amount, String invoice) {

        Transaction transaction = new Transaction();

        User user = (User) userService.loadUserByUsername(userName);
        transaction.setUser(user);

        Payee payee = payeeRepository.findByName(payeeName);
        transaction.setPayee(payee);

        transaction.setAmount(amount);
        transaction.setTimestamp(new Date());
        transaction.setInvoice(invoice);


        // TODO: 2018-06-15 check balance before proceed

        userService.changeBalance(userName, -amount);

        transactionRepository.save(transaction);

        return transaction;
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        transaction.setTimestamp(new Date());
        logger.info("Got transaction to check and save: {}", transaction);
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionList(User user) {

        return transactionRepository.getAll(user.getId());
    }
}

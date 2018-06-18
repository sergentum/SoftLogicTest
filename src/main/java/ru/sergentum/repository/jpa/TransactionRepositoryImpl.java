package ru.sergentum.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergentum.model.Transaction;
import ru.sergentum.repository.datajpa.CrudTransactionRepository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private CrudTransactionRepository crudTransactionRepository;

    @Autowired
    public TransactionRepositoryImpl(CrudTransactionRepository crudTransactionRepository) {
        this.crudTransactionRepository = crudTransactionRepository;
    }

    @Override
    public List<Transaction> getAll(Integer userId) {
        return crudTransactionRepository.getAll(userId);
    }

    @Override
    @Transactional
    public boolean save(Transaction transaction) {
        crudTransactionRepository.save(transaction);
        return true;
    }
}

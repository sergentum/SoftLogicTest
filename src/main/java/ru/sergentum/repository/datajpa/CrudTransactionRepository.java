package ru.sergentum.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;

import java.util.Date;
import java.util.List;

public interface CrudTransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> getAllByUserId(Integer userId);

    List<Transaction> getAllByTimestampBetweenAndUserId(Date startDate, Date endDate, Integer userId);

    List<Transaction> getAllByAmountBetweenAndUserId(Integer minAmount, Integer maxAmount, Integer userId);

    List<Transaction> getAllByPayeeIdAndUserId(Integer payeeId, Integer userId);
}

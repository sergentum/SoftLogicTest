package ru.sergentum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergentum.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

package ru.sergentum.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sergentum.model.Transaction;

import java.util.List;

public interface CrudTransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.user.id=:userId")
    List<Transaction> getAll(@Param("userId") int userId);
    void deleteAll();
}

package ru.sergentum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergentum.model.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Integer> {
    Payee findByName(String name);
}

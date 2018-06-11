package ru.sergentum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergentum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String s);

    User findUserByEmail(String s);
}

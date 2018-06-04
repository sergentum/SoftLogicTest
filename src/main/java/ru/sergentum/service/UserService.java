package ru.sergentum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sergentum.model.User;

public interface UserService extends UserDetailsService {
	User findUserByEmail(String email);
	void saveUser(User user);
}

package ru.sergentum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sergentum.model.User;

public interface UserService extends UserDetailsService {
	void saveNewUser(User user);
	void updateUser(User user);
	User loadUserByEmail(String s);
	void changeBalance(String s, Integer i);
}

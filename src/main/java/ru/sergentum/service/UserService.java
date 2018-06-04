package ru.sergentum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sergentum.model.User;

public interface UserService extends UserDetailsService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}

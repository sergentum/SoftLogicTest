package ru.sergentum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sergentum.model.User;

public interface UserService extends UserDetailsService {
	void saveUser(User user);
}

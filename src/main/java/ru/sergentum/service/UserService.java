package ru.sergentum.service;

import ru.sergentum.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}

package ru.sergentum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sergentum.model.Role;
import ru.sergentum.model.User;
import ru.sergentum.repository.RoleRepository;
import ru.sergentum.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) {
        // TODO: 2018-06-04  implement UserDetails by User
		return userRepository.findByEmail(s);
	}

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
		userRepository.save(user);
	}

}

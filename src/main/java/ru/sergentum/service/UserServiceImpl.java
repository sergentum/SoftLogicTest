package ru.sergentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergentum.model.Role;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.RoleRepository;
import ru.sergentum.repository.datajpa.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int START_BALANCE = 100;

	private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
	public UserDetails loadUserByUsername(String s) {
	    logger.debug("loadUserByUsername {}", s);
		return userRepository.findUserByUsername(s);
	}

    @Override
    public User loadUserByEmail(String s) {
        return userRepository.findUserByEmail(s);
    }

	public void saveNewUser(User user) {
        logger.debug("Register user: {}", user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByRole("USER")));
            user.setRoles(roles);
        }
		user.setBalance(START_BALANCE);

        user.setTransactions(new ArrayList<Transaction>());

		userRepository.save(user);
		logger.debug("User saved: {}", user);
	}

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
        logger.debug("User updated: {}", user);
    }

    @Override
    @Transactional
    public void changeBalance(String s, Integer i) {
        User user = (User) loadUserByUsername(s);
        logger.debug("User {} old balance: {}", s, user.getBalance());
        user.setBalance(user.getBalance() + i);
        logger.debug("User {} new balance: {}", s, user.getBalance());
    }
}

package ru.sergentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sergentum.model.Role;
import ru.sergentum.model.User;
import ru.sergentum.repository.RoleRepository;
import ru.sergentum.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static int startBalance = 100;

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String s) {
		return userRepository.findUserByUsername(s);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if (user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByRole("USER")));
            user.setRoles(roles);
        }
		user.setBalance(startBalance);
		userRepository.save(user);
		logger.debug("User {} saved", user);
	}
}

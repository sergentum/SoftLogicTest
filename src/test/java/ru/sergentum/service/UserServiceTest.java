package ru.sergentum.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Role;
import ru.sergentum.model.User;
import ru.sergentum.repository.PayeeRepository;
import ru.sergentum.repository.RoleRepository;
import ru.sergentum.repository.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration({
        "classpath:spring/spring-mvc.xml"
})
@WebAppConfiguration
@Transactional
public class UserServiceTest {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserService userService;

    @Test
    public void save_user_thenGetOk() {

        Role userRole = new Role("USER");
        roleRepository.save(userRole);

        User user = new User();
        user.setUsername("01234567890");
        user.setPassword("Password");
        user.setEmail("asd@asd.asd");

        userService.saveUser(user);

        User user1 = (User) userService.loadUserByUsername(user.getUsername());
        Assert.assertNotNull(user1);
        Assert.assertNotNull(user1.getRoles());
        Assert.assertTrue(user1.getRoles().contains(userRole));
    }
}

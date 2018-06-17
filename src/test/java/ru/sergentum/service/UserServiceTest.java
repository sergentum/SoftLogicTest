package ru.sergentum.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sergentum.controller.DBController;
import ru.sergentum.model.Role;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.RoleRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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

    @Autowired
    private DBController dbController;

    @Test
    public void save_user_thenGetOk() {
        dbController.initdb();
        User user = dbController.getTestUser();

        User user1 = (User) userService.loadUserByUsername(user.getUsername());
        Assert.assertNotNull(user1);
        Assert.assertNotNull(user1.getRoles());
        Role userRole = roleRepository.findByRole("USER");
        Assert.assertTrue(user1.getRoles().contains(userRole));
    }
}

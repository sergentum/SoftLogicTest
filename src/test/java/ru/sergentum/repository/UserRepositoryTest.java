package ru.sergentum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Role;
import ru.sergentum.model.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
public class UserRepositoryTest {

    @Resource
    private PayeeRepository payeeRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;


    @Test
    public void save_user_thenGetOk() {
        Payee payee = new Payee("OOO JKH" , 10, 30);
        payeeRepository.save(payee);

        Payee payee1 = payeeRepository.findByName(payee.getName());
        Assert.assertEquals(payee.getName(), payee1.getName());


        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");
        Set<Role> roles = new HashSet<>(Arrays.asList(userRole, adminRole));

        roleRepository.save(userRole);
        Role role = roleRepository.findByRole(userRole.getRole());
        Assert.assertEquals(userRole.getRole(), role.getRole());

        roleRepository.save(adminRole);
        role = roleRepository.findByRole(adminRole.getRole());
        Assert.assertEquals(adminRole.getRole(), role.getRole());


        User user = new User("01234567890", "Password", "asd@asd.asd", roles);
        userRepository.save(user);
        User user1 = userRepository.findUserByUsername(user.getUsername());
        Assert.assertNotNull(user1);
        Assert.assertNotNull(user1.getRoles());
        Assert.assertTrue(user1.getRoles().contains(userRole));

    }
}
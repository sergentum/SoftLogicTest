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
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
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
public class TransactionServiceTest {

    @Resource
    private PayeeRepository payeeRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserService userService;

    @Resource
    private TransactionService transactionService;

    @Test
    public void save_transaction_thenGetOk() {

        Payee payee = new Payee("OOO JKH" , 1, 10);
        payeeRepository.save(payee);

        Role userRole = new Role("USER");
        roleRepository.save(userRole);

        User user = new User();
        user.setUsername("01234567890");
        user.setPassword("Password");
        user.setEmail("asd@asd.asd");
        userService.saveNewUser(user);

        transactionService.doTransaction(user.getUsername(), payee.getName(), 1, "000000000000");
        transactionService.doTransaction(user.getUsername(), payee.getName(), 2, "000000000000");
        transactionService.doTransaction(user.getUsername(), payee.getName(), 3, "000000000000");

        Assert.assertFalse(transactionService.getTransactionList(user).isEmpty());
        Assert.assertTrue(transactionService.getTransactionList(user).size() == 3);

//        User dbUser = (User) userService.loadUserByUsername(user.getUsername());
//        Assert.assertEquals(94, dbUser.getBalance());

        for (Transaction transaction:transactionService.getTransactionList(user)) {
            System.out.println(transaction);
        }
    }
}

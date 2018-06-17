package ru.sergentum.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.sergentum.controller.DBController;
import ru.sergentum.model.Payee;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.RoleRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration({
        "classpath:spring/spring-mvc.xml"
})
@WebAppConfiguration
@Transactional
@Sql(scripts = "classpath:db/truncate.sql")
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    DBController dbController;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void save_transaction_thenGetOk() {
        dbController.initdb();
        User testUser = dbController.getTestUser();

        Payee payee = dbController.createSomePayee("TestPayee");

        dbController.createSomeTransaction(testUser, payee, 1);
        dbController.createSomeTransaction(testUser, payee, 2);
        dbController.createSomeTransaction(testUser, payee, 3);


        Assert.assertFalse(transactionService.getTransactionList(testUser).isEmpty());
        Assert.assertEquals(3, transactionService.getTransactionList(testUser).size());
    }
}

package ru.sergentum.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sergentum.controller.DBController;
import ru.sergentum.model.Payee;
import ru.sergentum.model.User;

import java.util.HashSet;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration({
        "classpath:spring/spring-mvc.xml"
})
@WebAppConfiguration
@Sql(scripts = "classpath:db/truncate.sql")
public class TransactionServiceMultiThreadTest {

    private Logger logger = LoggerFactory.getLogger(TransactionServiceMultiThreadTest.class);

    @Autowired
    DBController dbController;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    private static int THREAD_COUNT = 10;

    private static int TRANS_COUNT = 90;

    private static int START_BALANCE = 100;

    private static int TEST_BALANCE = 900;

    @Test
    public void save_Many_thenCalcBalance() {
        dbController.initdb();
        User testUser = dbController.getTestUser();
        userService.changeBalance(testUser.getUsername(), TEST_BALANCE);

        Payee payee = dbController.createSomePayee("TestPayee");

        Runnable task = () -> {
            for (int i = 0; i < TRANS_COUNT; i++) {
                dbController.createSomeTransaction(testUser, payee, 1);
            }
        };

        Set<Thread> threadSet = new HashSet<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(task);
            threadSet.add(thread);
            thread.start();
        }

        for (Thread thread:threadSet) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                logger.info("Interrupted exception {}", ex);
            }
        }

        int transactionCount = transactionService.getTransactionList(testUser).size();
        logger.info("User transactions count = {}", transactionCount);
        Assert.assertEquals(THREAD_COUNT * TRANS_COUNT, transactionCount);

        int totalBalance = ((User) userService.loadUserByUsername(testUser.getUsername())).getBalance();
        logger.info("User Total Balance = {}", totalBalance);
        Assert.assertEquals((START_BALANCE + TEST_BALANCE) - (THREAD_COUNT * TRANS_COUNT), totalBalance);
    }
}

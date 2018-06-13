package ru.sergentum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.sergentum.model.Payee;
import ru.sergentum.model.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
public class PayeeRepositoryTest {

    @Resource
    private PayeeRepository payeeRepository;

    @Test
    public void save_payee_thenGetOk() {
        Payee payee = new Payee("OOO JKH" , 10, 30);
        payeeRepository.save(payee);

        Payee payee1 = payeeRepository.findByName(payee.getName());
        Assert.assertEquals(payee.getName(), payee1.getName());
    }
}
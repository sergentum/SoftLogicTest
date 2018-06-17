package ru.sergentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Role;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
import ru.sergentum.repository.datajpa.RoleRepository;
import ru.sergentum.service.TransactionService;
import ru.sergentum.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class DBController {
//    private Logger logger = LoggerFactory.getLogger(DBController.class);

    private UserService userService;

    private RoleRepository roleRepository;

    private PayeeRepository payeeRepository;

    private TransactionService transactionService;

    private User testUser;

    @Autowired
    public DBController(UserService userService, RoleRepository roleRepository, PayeeRepository payeeRepository, TransactionService transactionService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.payeeRepository = payeeRepository;
        this.transactionService = transactionService;
    }

    public Payee createSomePayee(String payeeName) {
        // write payee to db if needed
        Payee payee = new Payee(payeeName, 10, 30);
        if (payeeRepository.findByName(payee.getName()) == null) {
            payeeRepository.save(payee);
        }
        return payeeRepository.findByName(payeeName);
    }

    public Set<Role> createRoles() {
        //write roles to db if needed
        HashSet<Role> roles = new HashSet<>(
                Arrays.asList(
                        new Role("ADMIN"), new Role("USER")
                )
        );
        HashSet<Role> rolesDb = new HashSet<>();
        for (Role role : roles) {
            if (roleRepository.findByRole(role.getRole()) == null) {
                roleRepository.save(role);
            }
            rolesDb.add(roleRepository.findByRole(role.getRole()));
        }
        return rolesDb;
    }

    public User createSomeUser(Set<Role> roles) {
        //write user to db if needed
        User user = new User();
        user.setEmail("asd@asd.asd");
        user.setPassword("123123");
        user.setUsername("01234567890");
        user.setRoles(roles);
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            userService.saveNewUser(user);
        }
        return (User) userService.loadUserByUsername(user.getUsername());
    }

    public void createSomeTransaction(User user, Payee payee, Integer amount){
        //write transaction to db if needed
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setPayee(payee);
        transaction.setAmount(amount);
        transaction.setInvoice("000000000000000");
        transactionService.save(transaction);
    }

    @RequestMapping(value = {"/initdb"}, method = RequestMethod.GET)
    public ModelAndView initdb() {
        createSomePayee("OOO JKH");
        createSomePayee("OOO PEK");
        createSomePayee("OOO Cafe");
        Set<Role> roles = createRoles();
        testUser = createSomeUser(roles);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("db");
        modelAndView.addObject("message", "DB init requested");
        return modelAndView;
    }

    public User getTestUser() {
        return testUser;
    }
}
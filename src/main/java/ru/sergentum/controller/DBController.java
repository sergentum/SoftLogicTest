package ru.sergentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

@Controller
public class DBController {

    private UserService userService;

    private RoleRepository roleRepository;

    private PayeeRepository payeeRepository;

    private TransactionService transactionService;

    @Autowired
    public DBController(UserService userService, RoleRepository roleRepository, PayeeRepository payeeRepository, TransactionService transactionService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.payeeRepository = payeeRepository;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = {"/initdb"}, method = RequestMethod.GET)
    private String initdb(ModelMap modelMap) {

        // write payee to db if needed
        Payee payee = new Payee("OOO JEK", 10, 30);
        if (payeeRepository.findByName(payee.getName()) == null) {
            payeeRepository.save(payee);
        }
        payee = payeeRepository.findByName(payee.getName());

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


        //write user to db if needed
        User user = new User();
        user.setEmail("asd@asd.asd");
        user.setPassword("123123");
        user.setUsername("01234567890");
        user.setRoles(rolesDb);
        User dbuser = (User) userService.loadUserByUsername(user.getUsername());
        if (dbuser == null) {
            userService.saveNewUser(user);
        }

        //write transaction to db if needed
        dbuser = (User) userService.loadUserByUsername(user.getUsername());

        Transaction transaction = new Transaction();
        transaction.setUser(dbuser);
        transaction.setPayee(payee);
        transaction.setAmount(2);
        transaction.setInvoice("000000000000000");

        transactionService.save(transaction);

        for (Transaction trans:transactionService.getTransactionList(dbuser)) {
            System.out.println(trans);
        }

        modelMap.addAttribute("message", "DB init requested");
        return "db";
    }

    @RequestMapping(value = {"/dropdb"}, method = RequestMethod.GET)
    private ModelAndView dropdb() {
        // TODO: 2018-06-10 implement db drop

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("db");
        modelAndView.addObject("message", "DB drop requested");
        return modelAndView;
    }
}
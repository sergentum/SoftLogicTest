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
import ru.sergentum.repository.PayeeRepository;
import ru.sergentum.repository.RoleRepository;
import ru.sergentum.repository.TransactionRepository;
import ru.sergentum.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class DBController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PayeeRepository payeeRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @RequestMapping(value = {"/initdb"}, method = RequestMethod.GET)
    private ModelAndView initdb() {

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


        //write user to db if nneded
        User user = new User();
        user.setEmail("asd@asd.asd");
        user.setPassword("123123");
        user.setName("Sergey");
        user.setLastName("Vorozhtsov");
        user.setActive(1);
        user.setBalance(100);
        user.setRoles(roles);
        System.out.println("User BEFORE saving " + user);
        User dbuser = userService.findUserByEmail(user.getEmail());
        if (dbuser == null) {
            System.out.println("user not found, insert new one " + user);
            userService.saveUser(user);
        } else {
            System.out.println("user found in db " + dbuser);
        }


        //write transaction to db if needed
        dbuser = userService.findUserByEmail(user.getEmail());
        System.out.println("User AFTER saving " + dbuser);
        Transaction transaction = new Transaction();
        transaction.setAmount(1234);
        transaction.setUser(dbuser);
        transaction.setPayee(payee);
        transactionRepository.save(transaction);

        for (Transaction trans : dbuser.getTransactions()) {
            System.out.println(trans);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("message");
        modelAndView.addObject("message", "DB init requested");
        return modelAndView;
    }

}

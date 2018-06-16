package ru.sergentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.sergentum.model.Payee;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
import ru.sergentum.service.TransactionService;
import ru.sergentum.service.UserService;

@Controller
public class AppController {

    private UserService userService;

    private TransactionService transactionService;

    private PayeeRepository payeeRepository;

    public AppController(UserService userService, TransactionService transactionService, PayeeRepository payeeRepository) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.payeeRepository = payeeRepository;
    }

    @RequestMapping(value = "/app/", method = RequestMethod.GET)
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) userService.loadUserByUsername(auth.getName());

		modelAndView.addObject("transactionList", transactionService.getTransactionList(user));
        modelAndView.addObject("user", user);
        modelAndView.setViewName("app/index");
        return modelAndView;
    }

}

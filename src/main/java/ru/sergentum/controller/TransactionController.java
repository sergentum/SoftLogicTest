package ru.sergentum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
import ru.sergentum.service.TransactionService;
import ru.sergentum.service.UserService;

import javax.validation.Valid;

@Controller
public class TransactionController {

    private PayeeRepository payeeRepository;

    private UserService userService;

    private TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(PayeeRepository payeeRepository, UserService userService, TransactionService transactionService) {
        this.payeeRepository = payeeRepository;
        this.userService = userService;
        this.transactionService = transactionService;
    }


    @RequestMapping(value = "/app/transaction", method = RequestMethod.GET)
    public ModelAndView getTransactionPage() {
        logger.info("start");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("payeeList", payeeRepository.findAll());

        modelAndView.setViewName("app/transaction");
        return modelAndView;
    }


    @RequestMapping(value = "/app/transaction", method = RequestMethod.POST)
    public ModelAndView postTransactionPage(
            @RequestParam("payeeName") String payeeName,
            @RequestParam("invoice") String invoice,
            @RequestParam("amount") Integer amount
    ) {
        logger.info("got parameters payee:{}, invoice:{}, amount:{}", payeeName, invoice, amount);
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        transactionService.doTransaction(userName, payeeName, amount, invoice);
        // TODO: 2018-06-15 check error and return success page or get back and highlight errors 
        
        modelAndView.setViewName("app/transaction");
        return modelAndView;
    }


    @RequestMapping(value = "/app/transactionSuccess", method = RequestMethod.GET)
    public ModelAndView getTransactionSuccessPage() {
        logger.info("start");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("payeeList", payeeRepository.findAll());

        modelAndView.setViewName("app/transactionSuccess");
        return modelAndView;
    }


}

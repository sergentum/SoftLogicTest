package ru.sergentum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sergentum.model.Payee;
import ru.sergentum.model.Transaction;
import ru.sergentum.model.User;
import ru.sergentum.repository.datajpa.PayeeRepository;
import ru.sergentum.service.TransactionService;
import ru.sergentum.service.UserService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

@Controller
public class TransactionController {

    private PayeeRepository payeeRepository;

    private TransactionService transactionService;

    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(PayeeRepository payeeRepository, TransactionService transactionService, UserService userService) {
        this.payeeRepository = payeeRepository;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/app/transaction", method = RequestMethod.GET)
    public ModelAndView getTransactionPage(ModelMap model) {
        logger.info("start");
        ModelAndView modelAndView = new ModelAndView();

        if (!model.containsAttribute("transaction")) {
            Transaction transaction = new Transaction();

            model.addAttribute("transaction", transaction);
        }

        model.addAttribute("payeeList", payeeRepository.findAll());
        modelAndView.addAllObjects(model);

        modelAndView.setViewName("app/transaction");
        return modelAndView;
    }

    @RequestMapping(value = "/app/transaction", method = RequestMethod.POST)
    public ModelAndView postTransactionPage(
            @Valid Transaction transaction,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        logger.info("got transaction to save: {}", transaction);

        ModelAndView modelAndView = new ModelAndView();

        if (transaction.getUser() == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName();
            User user = (User) userService.loadUserByUsername(userName);
            transaction.setUser(user);
        }

        boolean successSave = false;

        if (!bindingResult.hasErrors()) {
            try {
                transactionService.save(transaction);
                successSave = true;
            } catch (Exception ex) {
                modelAndView.addObject("exception", ex.getLocalizedMessage());
                redirectAttributes.addFlashAttribute("exception", ex.getLocalizedMessage());
                logger.warn("exception while save transaction {}", ex.getLocalizedMessage());
            }
        } else {
            logger.warn("binding errors occurs {}", bindingResult);
        }

        if (successSave) {
            modelAndView.setViewName("redirect:/app/");
        } else {
            redirectAttributes.addFlashAttribute("transaction", transaction);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.transaction", bindingResult);
            modelAndView.setViewName("redirect:/app/transaction");
        }

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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Payee.class, "payee", new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) {
                logger.info("Binder tries to find: {} ", text);
                Integer id = Integer.parseInt(text);
                Payee payee = payeeRepository.findById(id).orElse(null);
                logger.info("Binder found: {}", payee);
                setValue(payee);
            }
        });
    }

}

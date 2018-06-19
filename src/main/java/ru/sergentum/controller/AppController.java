package ru.sergentum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sergentum.model.User;
import ru.sergentum.service.TransactionService;
import ru.sergentum.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class AppController {

    private Logger logger = LoggerFactory.getLogger(DBController.class);

    private UserService userService;

    private TransactionService transactionService;

    public AppController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/app/", method = RequestMethod.GET)
    public ModelAndView getHomepage(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime
    ) {
        ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) userService.loadUserByUsername(auth.getName());
        logger.debug("Got filter params {} {}", startDate, startTime);
		modelAndView.addObject("transactionList", transactionService.getTransactionList(user));
        modelAndView.addObject("user", user);
        modelAndView.setViewName("app/index");
        return modelAndView;
    }

}

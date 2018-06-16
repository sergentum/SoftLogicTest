package ru.sergentum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.sergentum.model.User;
import ru.sergentum.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        logger.debug("Got user to register: {}", user);

        ModelAndView modelAndView = new ModelAndView();
        User userExists = (User) userService.loadUserByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult
                    .rejectValue("username",
                            "error.user",
                            "There is already a user registered with the phone provided");
            logger.warn("User already exist {}", userExists);
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult
                    .rejectValue("passwordConfirm",
                            "error.user",
                            "Passwords should be the same");
        }

        if (!user.getEmail().isEmpty() && userService.loadUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.username", "Email already registered!");
        }

        if (bindingResult.hasErrors()) {
            logger.warn("binding errors occurs {}", bindingResult);
            modelAndView.setViewName("registration");

        } else {
            logger.debug("Register user: {}", user);
            logger.warn("binding result: {}", bindingResult);
            userService.saveNewUser(user);
            modelAndView.addObject("message", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("redirect:/app/");

        }
        return modelAndView;
    }
}

package ru.sergentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

	@Autowired
    private PayeeRepository payeeRepository;

	@Autowired
    private TransactionRepository transactionRepository;

	@RequestMapping(value={"/initdb"}, method = RequestMethod.GET)
    private ModelAndView initdb(){

        // write payee to db if needed
        Payee payee = new Payee("OOO JEK", 10, 30);
        if (payeeRepository.findByName(payee.getName()) == null){
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
        for (Role role: roles) {
            if (roleRepository.findByRole(role.getRole()) == null){
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
        if (userService.findUserByEmail(user.getEmail()) == null){
            userService.saveUser(user);
        }
        user = userService.findUserByEmail(user.getEmail());

        //write transaction to db if needed
        Transaction transaction = new Transaction();
        transaction.setAmount(1234);
        transaction.setUser(user);
        transaction.setPayee(payee);
        transactionRepository.save(transaction);

        for (Transaction trans : user.getTransactions()) {
            System.out.println(trans);
        }
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("initdb");
		return modelAndView;
	}

	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		UserDetails user = userService.loadUserByUsername(auth.getName());
		String errorMessage = "";
		if (user != null) {
			modelAndView.setViewName("redirect:app/");
		} else {
			modelAndView.setViewName("login");
			errorMessage = "Email or Password invalid, please verify";
		}
		modelAndView.addObject("errorMessage", errorMessage);
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/app/", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findUserByEmail(auth.getName());
//		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("app/index");
		return modelAndView;
	}

	// TODO: 2018-06-07 lkjasdfjkj 
}

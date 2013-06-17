package ru.spb.petrk.webissues.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spb.petrk.webissues.utils.Constants;
import ru.spb.petrk.webissues.utils.MessageCollector;
import ru.spb.petrk.webissues.model.User;
import ru.spb.petrk.webissues.repositories.UsersRepository;
import ru.spb.petrk.webissues.security.Role;
import ru.spb.petrk.webissues.validators.UserValidator;

/**
 *
 * @author PetrK
 */

@Controller
public class AdminController {
    
    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @RequestMapping(value = "/app/admin/", method = RequestMethod.GET)   
    public ModelAndView index() {             
        ModelAndView modelAndView = new ModelAndView("admin_page");                
        Iterable<User> users = usersRepository.findAll();        
        modelAndView.addObject(Constants.USERS, users);
        return modelAndView; 
    }
    
    @RequestMapping(value = "/app/admin/createUser", method = RequestMethod.GET)
    public String createUser() {
        return "create_user";
    }       

    @Transactional
    @RequestMapping(value = "/app/admin/blockUser", method = RequestMethod.GET)    
    public ModelAndView blockUser(@RequestParam String login) {
        User user = usersRepository.findByLogin(login);
        
        if (user != null) {
            user.setBlocked(true);
            usersRepository.save(user);
        }
        
        ModelAndView modelAndView = new ModelAndView("admin_page");
        Iterable<User> users = usersRepository.findAll();        
        modelAndView.addObject(Constants.USERS, users);
        
        return modelAndView;
    }
    
    @Transactional
    @RequestMapping(value = "/app/admin/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam String login,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam(required = false) Boolean roleAdmin,
                                 @RequestParam(required = false) Boolean roleUser,
                                 @RequestParam(required = false) Boolean roleObserver) 
    {
        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        List<Role> roles = new ArrayList<Role>();
        if (roleAdmin != null) {
            roles.add(Role.Admin);
        }
        if (roleUser != null) {
            roles.add(Role.User);
        }
        if (roleObserver != null) {
            roles.add(Role.Observer);
        }        
        user.setRoles(roles);        
        
        ModelAndView modelAndView;
                        
        UserValidator validator = new UserValidator(usersRepository);
        MessageCollector collector = new MessageCollector();
        
        if (validator.validate(collector, user)) {        
            usersRepository.save(user);
            
            modelAndView = new ModelAndView("admin_page");            
            Iterable<User> users = usersRepository.findAll();        
            modelAndView.addObject(Constants.USERS, users);
        } else {
            modelAndView = new ModelAndView("create_user");
            modelAndView.addObject(Constants.USER, user);
            modelAndView.addObject(Constants.MESSAGE_COLLECTOR, collector);
        }
        
        return modelAndView;
    }    
}

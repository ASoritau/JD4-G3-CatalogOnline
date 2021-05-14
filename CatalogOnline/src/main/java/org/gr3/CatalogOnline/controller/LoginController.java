package org.gr3.CatalogOnline.controller;

import org.gr3.CatalogOnline.model.User;
import org.gr3.service.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private List<User> loggedInUsers = new ArrayList();


    @RequestMapping("/")
    public RedirectView redirectWithUsingRedirectView(@RequestParam(required = false, name = "username") String username) {
        for (User user : loggedInUsers) {
            if (user.getUsername().equals(username)) {
                return new RedirectView(userOverview());
            }
        }

        return new RedirectView(login((Model) new User()));
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("", user);
        return "login";
    }

    @GetMapping(value = "/dashboard")
    public String userOverview() {
        return "dashboard";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String checkLoggedInUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        throw new NotYetImplementedException();
    }

}
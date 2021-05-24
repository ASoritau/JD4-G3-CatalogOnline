package org.gr3.controller;

import org.gr3.model.Student;
import org.gr3.model.Teacher;
import org.gr3.model.User;
import org.gr3.service.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private List<User> loggedInUsers = new ArrayList();

//    @RequestMapping("/")
//    public RedirectView redirectWithUsingRedirectView(@RequestParam(required = false, name = "username") String username) {
//        for (User user : loggedInUsers) {
//            if (user.getUsername().equals(username)) {
//                return new RedirectView(userOverview());
//            }
//        }
//
//        return new RedirectView(login((Model) new Student()));
//    }

    @RequestMapping("/")
    public String redirectWithUsingRedirectView(Model model) {
        return login(model);
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
        User loginUser = userService.login(user);

        if (loginUser != null) {
//            ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
//            modelAndView.addObject("user", loginUser);
//            return modelAndView;
            redirectAttributes.addFlashAttribute("user", loginUser);
//            redirectAttributes.addAttribute("user1", loginUser.getEmail());
            return "redirect:/dashboard";
        }
        else {
//            return new ModelAndView("error");
            return "Error";
        }
    }

    @RequestMapping("/register")
    public String registrationPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);

        return "register";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }

        userService.register(user);
        System.out.println(user);

        return "login";
    }

    @GetMapping(path = "/allUsers")
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/username")
    public @ResponseBody
    User getUser() {
        return userService.findByName("Ana");
    }

    @RequestMapping(value = "/dashboard")
    public ModelAndView myDashboardPage(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
//        de cautat tutorial thymeleaf: cum se trimit corect parametrii (ajunge model, nu ajunge cheia?)
        if (user == null) {
            return new ModelAndView("error");
        }

        if (user instanceof Student) {
            model.addAttribute("student", (Student)user);
            return new ModelAndView("redirect:/studentDashboard");
        }

        if (user instanceof Teacher) {
            model.addAttribute("teacher", (Teacher)user);
            return new ModelAndView("redirect:/teacherDashboard");
        }

        return new ModelAndView("error");
    }

}
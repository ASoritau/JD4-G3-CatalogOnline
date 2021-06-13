package org.gr3.controller;

import org.gr3.model.Student;
import org.gr3.model.Subject;
import org.gr3.model.Teacher;
import org.gr3.model.User;
import org.gr3.service.SubjectService;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes("user")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    private List<User> loggedInUsers = new ArrayList();

    @RequestMapping("/")
    public String redirectWithUsingRedirectView(Model model) {
        return login(model);
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "loginPage";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
        User loginUser = userService.login(user);

        if (loginUser != null) {
            redirectAttributes.addFlashAttribute("user", loginUser);
            return "redirect:/dashboard";
        } else {
            return "error";
        }
    }

    @RequestMapping("/register")
    public String registrationPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);

        return "register";
    }

    @RequestMapping("/registerTeacher")
    public String registerTeacher(Model model, RedirectAttributes redirectAttributes) {
        Teacher emptyUser = new Teacher("Teacher");
        model.addAttribute("teacher", emptyUser);
        redirectAttributes.addFlashAttribute("user", emptyUser);

        return "teacherRegisterPage";
    }

    @RequestMapping("/registerStudent")
    public String registerStudent(Model model, RedirectAttributes redirectAttributes) {
        Student emptyUser = new Student("Student");
        model.addAttribute("user", emptyUser);
        redirectAttributes.addFlashAttribute("user", emptyUser);

        return "studentRegisterPage";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }

        userService.register(user);
        System.out.println(user);

        return "loginPage";
    }

    @RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
    public String saveTeacher(@ModelAttribute("teacher") @Validated Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }

        List<Subject> existingSubjects = subjectService.getAllSubjects();

        if (existingSubjects.stream().noneMatch(s -> s.getName().equals(teacher.getSubject()))) {
            subjectService.createSubject(new Subject(teacher.getSubject()));
        }

        userService.registerTeacher(teacher);
        System.out.println(teacher);

        return "loginPage";
    }

    @GetMapping(path = "/allUsers")
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/username")
    public @ResponseBody
    User getUser(String name) {
        return userService.findByFirstName(name);
    }

    @RequestMapping(value = "/dashboard")
    public String myDashboardPage(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        if (user == null) {
            return "error";
        }

        if (user instanceof Student) {
            redirectAttributes.addFlashAttribute("student", user);
            return "redirect:/studentDashboard";
        }

        if (user instanceof Teacher) {
            redirectAttributes.addFlashAttribute("teacher", user);
            return "redirect:/teacherDashboardV2";
        }

        return "error";
    }

}
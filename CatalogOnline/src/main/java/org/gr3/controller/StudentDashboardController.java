package org.gr3.controller;

import org.gr3.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes("student")
@Controller
public class StudentDashboardController {
    @RequestMapping(value = "/studentDashboard")
    public String myDashboardPage(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("student", student);
        student = (Student) model.getAttribute("student");
        redirectAttributes.addFlashAttribute("student", student);
        return "studentDashboard";
    }
}

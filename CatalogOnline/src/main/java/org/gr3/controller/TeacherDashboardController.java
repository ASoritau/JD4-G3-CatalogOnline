package org.gr3.controller;

import org.gr3.model.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes("teacher")
@Controller
public class TeacherDashboardController {
    @RequestMapping(value = "/teacherDashboard")
    public String myDashboardPage(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("teacher", teacher);
        teacher = (Teacher) model.getAttribute("teacher");
        redirectAttributes.addFlashAttribute("teacher", teacher);
        return "teacherDashboard";
    }
}

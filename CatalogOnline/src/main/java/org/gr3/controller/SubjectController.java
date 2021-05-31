package org.gr3.controller;

import org.gr3.model.Subject;
import org.gr3.model.Teacher;
import org.gr3.model.User;
import org.gr3.service.SubjectService;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adaugareMaterie", method = RequestMethod.GET)
    public String addSubjectForm(Model model) {
        Subject subject = new Subject();
        populateForm(model);
        model.addAttribute(subject);

        return "adaugareMaterie";
    }

    @RequestMapping(value = "/createSubject", method = RequestMethod.POST)
    public String createSubject(@ModelAttribute("subject") Subject subject, BindingResult errors, Model model) {
        subjectService.createSubject(subject);

        return "redirect:materieAdaugata";
    }

    @RequestMapping(value = "/materieAdaugata", method = RequestMethod.GET)
    public String subjectAdded(Model model) {
        return "materieAdaugata";
    }

    private void populateForm(Model model) {
        model.addAttribute("teachers", userService.getAllUsers().stream().filter(k -> (k instanceof Teacher))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
    }
}

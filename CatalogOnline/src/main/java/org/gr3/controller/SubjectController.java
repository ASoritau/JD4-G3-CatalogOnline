package org.gr3.controller;

import org.gr3.model.Subject;
import org.gr3.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/adaugareMaterie", method = RequestMethod.GET)
    public String addSubjectForm(Model model) {
        Subject subject = new Subject();
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
}

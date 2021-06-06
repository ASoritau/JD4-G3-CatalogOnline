package org.gr3.controller;

import org.gr3.model.Absence;
import org.gr3.model.Student;
import org.gr3.model.Subject;
import org.gr3.model.User;
import org.gr3.service.AbsenceService;
import org.gr3.service.SubjectService;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AbsenceController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private AbsenceService absenceService;

    @RequestMapping(value = "/absenceForm", method = RequestMethod.GET)
    public String showAbsenceForm(Model model) {
        populateForm(model);
        model.addAttribute("absence", new Absence());
        return "adaugareabsente";
    }

    @RequestMapping(value = "/getAbsences", method = RequestMethod.GET)
    public String getAbsences(Model model) {
        populateForm(model);
        List<Absence> absences = absenceService.getAllAbsences();

        for (Absence absence : absences) {
            Optional<User> user = userService.findById(17);
            user.ifPresent(value -> absence.setStudent_name(value.getFirstName() + " " + value.getLastName()));
        }

        model.addAttribute("absences", absences);
        return "absente";
    }

    @RequestMapping(value = "/createAbsence", method = RequestMethod.POST)
    public String createAbsence(@ModelAttribute("absence") Absence absence, BindingResult errors, Model model) {
        populateForm(model);
        Optional<User> user = userService.findById(17);
        user.ifPresent(value -> absence.setStudent_name(value.getFirstName() + " " + value.getLastName()));
        absenceService.createAbsence(absence);

        //reset form
        model.addAttribute("absence", new Absence());
        return "adaugareabsente";
    }

    private void populateForm(Model model) {
        model.addAttribute("students", userService.getAllUsers().stream().filter(k -> (k instanceof Student))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
        model.addAttribute("subjects", subjectService.getAllSubjects().stream()
                .collect(Collectors.toMap(Subject::getName, k -> (k.getName()))));
//        model.addAttribute("absences", absenceService.getAllAbsences().stream()
//                .collect(Collectors.toMap(Absence::getId, k -> (k.getDate()))));
    }

}

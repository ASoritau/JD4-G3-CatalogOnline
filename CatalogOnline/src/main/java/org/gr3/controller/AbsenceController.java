package org.gr3.controller;

import org.gr3.model.*;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SessionAttributes({"student", "teacher"})
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

        return "AddAbsencePage";
    }

    @RequestMapping(value = "/getAbsences", method = RequestMethod.GET)
    public String getAbsences(Model model) {
        Student student = (Student) model.getAttribute("student");

        populateForm(model);
        List<Absence> absences = absenceService.getStudentAbsences((int) student.getUserId());

        for (Absence absence : absences) {
            Optional<User> user = userService.findById(student.getUserId());
            user.ifPresent(value -> absence.setStudentName(value.getFirstName() + " " + value.getLastName()));
        }

        model.addAttribute("absences", absences);
        return "absente";
    }

    @RequestMapping(value = "/createAbsence", method = RequestMethod.POST)
    public String createAbsence(@ModelAttribute("absence") Absence absence, BindingResult errors, Model model) {
        Teacher teacher = (Teacher) model.getAttribute("teacher");
        String studentName = absence.getStudentName();

        if (!studentName.equals("")) {
            List<String> splittedStudentName = Arrays.asList(studentName.split("\\s+"));
            User user = userService.findByFirstNameAndLastName(splittedStudentName.get(0), splittedStudentName.get(1));

            absence.setStudentId((int) user.getUserId());
            absence.setSubjectName(teacher.getSubject());
            absence.setSubjectId(teacher.getSubjectId());

            absenceService.createAbsence(absence);

            //reset form
            model.addAttribute("absence", new Absence());
        }

        return "redirect:/teacherDashboard";
    }

    private void populateForm(Model model) {
        model.addAttribute("students", userService.getAllUsers().stream().filter(k -> (k instanceof Student))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
        model.addAttribute("subjects", subjectService.getAllSubjects().stream()
                .collect(Collectors.toMap(Subject::getName, k -> (k.getName()))));
    }
}

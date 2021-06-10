package org.gr3.controller;

import org.gr3.model.Grade;
import org.gr3.model.Student;
import org.gr3.model.Subject;
import org.gr3.model.User;
import org.gr3.service.GradeService;
import org.gr3.service.SubjectService;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SessionAttributes("student")
@Controller
public class GradesController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "/adaugarenote", method = RequestMethod.GET)
    public String showGradeForm(Model model) {
        populateForm(model);
        model.addAttribute("grade", new Grade());
        return "adaugarenote";
    }

    @RequestMapping(value = "/createGrade", method = RequestMethod.POST)
    public String crateGrade(@ModelAttribute("grade") Grade grade, BindingResult errors, Model model) {
        populateForm(model);
        Optional<User> user = userService.findById(grade.getStudentId());
        user.ifPresent(value -> grade.setStudentName(value.getFirstName() + " " + value.getLastName()));
        gradeService.crateGrade(grade);

        //reset form
        model.addAttribute("grade", new Grade());
        return "redirect:notaAdaugata";
    }

    @GetMapping(value = "/getGrades")
    public ModelAndView getGrades(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        student = (Student) model.getAttribute("student");
        ModelAndView model1 = new ModelAndView("note");

        populateForm(model);
        List<Grade> grades = gradeService.getStudentGrades((int) student.getUserId());

        for (Grade grade : grades) {
            Optional<User> user = userService.findById(student.getUserId());
            user.ifPresent(value -> grade.setStudentName(value.getFirstName() + " " + value.getLastName()));
        }

        model.addAttribute("grades", grades);
        return model1;
    }

    @RequestMapping(value = "/notaAdaugata", method = RequestMethod.GET)
    public String gradeAdded(Model model) {
        return "notaAdaugata";
    }

    private void populateForm(Model model) {
        model.addAttribute("students", userService.getAllUsers().stream().filter(k -> (k instanceof Student))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
        model.addAttribute("subjects", subjectService.getAllSubjects().stream()
//                .collect(Collectors.toMap(Subject::getId, k -> (k.getName()))));
                .collect(Collectors.toMap(Subject::getName, k -> (k.getName()))));
        model.addAttribute("grades", new ArrayList<>(gradeService.getPossibleGrades()));
    }
}

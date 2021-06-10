package org.gr3.controller;

import org.gr3.model.*;
import org.gr3.service.*;
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

@SessionAttributes({"student", "teacher"})
@Controller
public class GradesController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/adaugarenote", method = RequestMethod.GET)
    public String showGradeForm(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes, Model model) {
//        populateForm(model);

        model.addAttribute("grade", new Grade());
        model.addAttribute("teacher", teacher);
        teacher = (Teacher) model.getAttribute("teacher");
        populateTeacherForm(model, (int) teacher.getUserId());

        Connection teacherSubject = teacherService.getTeacherSpecialization((int) teacher.getUserId());
        teacher.setSubjectId(teacherSubject.getSubjectId());

        redirectAttributes.addFlashAttribute("teacher", teacher);

        return "adaugarenote";
    }

    @RequestMapping(value = "/createGrade", method = RequestMethod.POST)
    public String crateGrade(@ModelAttribute("grade") Grade grade, BindingResult errors, Model model) {
        populateForm(model);
//        populateTeacherForm(model, (int) teacher.getUserId());
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

    private void populateTeacherForm(Model model, int teacherId) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        List<Connection> connections = connectionService.getAllConnections();

        Optional<Connection> teacherConnection = connections.stream().filter(connection -> teacherId == connection.getUserId()).findFirst();
        List<Subject> teacherSubjects = allSubjects.stream().filter(subject -> teacherConnection.get().getSubjectId() == subject.getId()).collect(Collectors.toList());
//        List<Subject> teacherSubjects = (List<Subject>) allSubjects.stream().filter(subject -> teacherId == teacherConnection.get().getSubjectId());

        model.addAttribute("students", userService.getAllUsers().stream().filter(k -> (k instanceof Student))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
        model.addAttribute("subjects", teacherSubjects.stream()
//                .collect(Collectors.toMap(Subject::getId, k -> (k.getName()))));
                .collect(Collectors.toMap(Subject::getName, k -> (k.getName()))));
        model.addAttribute("grades", new ArrayList<>(gradeService.getPossibleGrades()));
    }
}

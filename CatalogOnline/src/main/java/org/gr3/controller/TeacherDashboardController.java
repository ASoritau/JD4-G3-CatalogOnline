package org.gr3.controller;

import org.gr3.model.Absence;
import org.gr3.model.Grade;
import org.gr3.model.Student;
import org.gr3.model.Teacher;
import org.gr3.model.dashboard.TeacherDashboardEntry;
import org.gr3.service.AbsenceService;
import org.gr3.service.GradeService;
import org.gr3.service.StudentService;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SessionAttributes("teacher")
@Controller
public class TeacherDashboardController {

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    AbsenceService absenceService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/teacherDashboard")
    public String myDashboardPageV2(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("teacher", teacher);
        teacher = (Teacher) model.getAttribute("teacher");
        List<Grade> grades = gradeService.getAllGrades();
        redirectAttributes.addFlashAttribute("teacher", teacher);
        redirectAttributes.addAttribute("grades", grades);

        String teacherSubject = teacher.getSubject();
        List<Grade> subjectGrades = grades.stream().filter(k -> k.getSubject().equals(teacherSubject)).collect(Collectors.toList());
        List<TeacherDashboardEntry> dashboardData = getRequiredStudentsData(subjectGrades);
        model.addAttribute("dashboardEntries", dashboardData);

        return "TeacherDashboard";
    }

    private List<TeacherDashboardEntry> getRequiredStudentsData(List<Grade> subjectGrades) {
        List<Student> students = studentService.getAllStuents();
        List<Absence> absences = absenceService.getAllAbsences();

        List<Student> requiredStudents = new ArrayList<>();

        for (Grade grade : subjectGrades) {
            Student student = students.stream().filter(s -> s.getUserId() == grade.getStudentId()).findFirst().get();

            if (!requiredStudents.contains(student)) {
                requiredStudents.add(student);
            }
        }

        List<TeacherDashboardEntry> dashboardEntries = new ArrayList<>();

        for (Student student : requiredStudents) {
            List<Grade> studentGrades = subjectGrades.stream().filter(g -> g.getStudentId() == student.getUserId()).collect(Collectors.toList());
            List<Absence> studentAbsences = absences.stream().filter(a -> a.getStudentId() == student.getUserId()).collect(Collectors.toList());

            TeacherDashboardEntry dashboardEntry = new TeacherDashboardEntry(student, studentGrades, studentAbsences);
            dashboardEntries.add(dashboardEntry);
        }

        return dashboardEntries;
    }
}

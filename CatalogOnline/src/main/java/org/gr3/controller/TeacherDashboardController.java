package org.gr3.controller;

import org.gr3.model.Absence;
import org.gr3.model.Grade;
import org.gr3.model.Student;
import org.gr3.model.Teacher;
import org.gr3.service.AbsenceService;
import org.gr3.service.GradeService;
import org.gr3.service.StudentService;
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

@SessionAttributes("teacher")
@Controller
public class TeacherDashboardController {

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    AbsenceService absenceService;

    @RequestMapping(value = "/teacherDashboard")
    public String myDashboardPage(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("teacher", teacher);
        teacher = (Teacher) model.getAttribute("teacher");
        List<Grade> grades = gradeService.getAllGrades();
        redirectAttributes.addFlashAttribute("teacher", teacher);
        redirectAttributes.addAttribute("grades", grades);
        return "teacherDashboard";
    }

    private List<List<String>> getStudentsData() {
        List<List<String>> dataToReturn = new ArrayList<>();

        List<Student> students = studentService.getAllStuents();
        List<Grade> grades = gradeService.getAllGrades();
        List<Absence> absences = absenceService.getAllAbsences();

        for (Grade grade : grades) {
            List<String> gradeData = new ArrayList<>();

            Student student = students.stream().filter(k -> k.getUserId() == grade.getStudentId()).findFirst().get();
            String studentName = student.getFirstName() + " " + student.getLastName();

            gradeData.add(studentName);
            gradeData.add(Integer.toString(grade.getGrade()));
            gradeData.add(grade.getDate().toString());

            dataToReturn.add(gradeData);
        }

        return dataToReturn;
    }
}

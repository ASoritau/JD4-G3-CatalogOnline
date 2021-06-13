package org.gr3.controller;

import org.gr3.model.*;
import org.gr3.service.*;
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
    public String myDashboardPage(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("teacher", teacher);
        teacher = (Teacher) model.getAttribute("teacher");
        List<Grade> grades = gradeService.getAllGrades();
        redirectAttributes.addFlashAttribute("teacher", teacher);
        redirectAttributes.addAttribute("grades", grades);

        String teacherSubject = teacher.getSubject();

        Student student_RaduAndreescu = (Student) userService.findByFirstNameAndLastName("Radu", "Andreescu");
        List<Grade> grades_RaduAndreescu = gradeService.getStudentGradesForSubject((int) student_RaduAndreescu.getUserId(), teacherSubject);

        Student student_AlinPascu = (Student) userService.findByFirstNameAndLastName("Alin", "Pascu");
        List<Grade> grades_AlinPascu = gradeService.getStudentGradesForSubject((int) student_AlinPascu.getUserId(), teacherSubject);

        Student student_RaduPop = (Student) userService.findByFirstNameAndLastName("Radu", "Pop");
        List<Grade> grades_RaduPop = gradeService.getStudentGradesForSubject((int) student_RaduPop.getUserId(), teacherSubject);

        model.addAttribute("grades_RaduAndreescu", grades_RaduAndreescu);
        model.addAttribute("grades_AlinPascu", grades_AlinPascu);
        model.addAttribute("grades_RaduPop", grades_RaduPop);

        List<Absence> absences_RaduAndreescu = absenceService.getStudentAbsencesForSubject((int) student_RaduAndreescu.getUserId(), teacherSubject);
        List<Absence> absences_AlinPascu = absenceService.getStudentAbsencesForSubject((int) student_AlinPascu.getUserId(), teacherSubject);
        List<Absence> absences_RaduPop = absenceService.getStudentAbsencesForSubject((int) student_RaduPop.getUserId(), teacherSubject);

        model.addAttribute("absences_RaduAndreescu", absences_RaduAndreescu);
        model.addAttribute("absences_AlinPascu", absences_AlinPascu);
        model.addAttribute("absences_RaduPop", absences_RaduPop);

        return "TeacherDashboard";
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

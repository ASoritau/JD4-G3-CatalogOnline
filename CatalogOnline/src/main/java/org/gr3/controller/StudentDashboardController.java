package org.gr3.controller;

import org.gr3.model.Absence;
import org.gr3.model.Grade;
import org.gr3.model.Student;
import org.gr3.model.Subject;
import org.gr3.service.AbsenceService;
import org.gr3.service.GradeService;
import org.gr3.service.SubjectService;
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

@SessionAttributes("student")
@Controller
public class StudentDashboardController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AbsenceService absenceService;

    @RequestMapping(value = "/studentDashboard")
    public String myDashboardPage(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
        model.addAttribute("student", student);
        student = (Student) model.getAttribute("student");
        redirectAttributes.addFlashAttribute("student", student);

        int studentId = (int) student.getUserId();
        List<Grade> grades = gradeService.getStudentGrades(studentId);
        model.addAttribute("grades", grades);

        List<Grade> grades_LimbaFranceza = gradeService.getStudentGradesForSubject(studentId, "Limba franceza");
        List<Grade> grades_Matematica = gradeService.getStudentGradesForSubject(studentId, "Matematica");
        List<Grade> grades_LimbaEngleza = gradeService.getStudentGradesForSubject(studentId, "Limba engleza");

        model.addAttribute("grades_LimbaFranceza", grades_LimbaFranceza);
        model.addAttribute("grades_Matematica", grades_Matematica);
        model.addAttribute("grades_LimbaEngleza", grades_LimbaEngleza);

        List<Absence> absences = absenceService.getStudentAbsences(studentId);
        model.addAttribute("absences", absences);

        List<Absence> absences_LimbaFranceza = absenceService.getStudentAbsencesForSubject(studentId, "Limba franceza");
        List<Absence> absences_Matematica = absenceService.getStudentAbsencesForSubject(studentId, "Matematica");
        List<Absence> absences_LimbaEngleza = absenceService.getStudentAbsencesForSubject(studentId, "Limba engleza");

        model.addAttribute("absences_LimbaFranceza", absences_LimbaFranceza);
        model.addAttribute("absences_Matematica", absences_Matematica);
        model.addAttribute("absences_LimbaEngleza", absences_LimbaEngleza);

        return "StudentDashboard";
    }
}

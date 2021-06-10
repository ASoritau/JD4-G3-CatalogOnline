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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TeacherDashboardController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "/dashboardNoteProf", method = RequestMethod.GET)
    public String showAbsenceForm(Model model) {
        populateForm(model);
        List<Grade> grades =  gradeService.getAllGrades();
        TeacherDashboardRow row = new TeacherDashboardRow(grades.get(0));

        return "dashboardNoteProf";
    }

    private void populateForm(Model model) {
        model.addAttribute("students", userService.getAllUsers().stream().filter(k -> (k instanceof Student))
                .collect(Collectors.toMap(User::getUserId, k -> (k.getFirstName() + " " + k.getLastName()))));
        model.addAttribute("subjects", subjectService.getAllSubjects().stream()
                .collect(Collectors.toMap(Subject::getSubjectId, k -> (k.getName()))));
    }

    private class TeacherDashboardRow {
        private String studentName;

        private Date gradeDate;

        private int grade;

        private String subjectName;

        public TeacherDashboardRow() {

        }

        public TeacherDashboardRow(Grade grade) {
            gradeDate = grade.getDate();
            this.grade = grade.getGrade();

            Student student = (Student) userService.findById(grade.getStudent_id());
            Subject subject = subjectService.findBySubjectId(grade.getSubject_id());
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public Date getGradeDate() {
            return gradeDate;
        }

        public void setGradeDate(Date gradeDate) {
            this.gradeDate = gradeDate;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }
    }
}

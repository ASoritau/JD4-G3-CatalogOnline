package org.gr3.model.dashboard;

import org.gr3.model.Absence;
import org.gr3.model.Grade;
import org.gr3.model.Student;
import org.gr3.model.utils.Pair;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TeacherDashboardEntry {
    public TeacherDashboardEntry(Student student, List<Grade> grades, List<Absence> absences) {
        studentName = student.getFirstName() + " " + student.getLastName();
        this.grades = new ArrayList<>();
        this.absenceDates = new ArrayList<>();

        for (Grade grade : grades) {
            this.grades.add(new Pair<>(grade.getGrade(), grade.getDate()));
        }

        for (Absence absence : absences) {
            absenceDates.add(absence.getDate());
        }
    };

    private String studentName;

    private List<Pair<Integer, Date>> grades;

    private List<Date> absenceDates;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Pair<Integer, Date>> getGrades() {
        return grades;
    }

    public void setGrades(List<Pair<Integer, Date>> grades) {
        this.grades = grades;
    }

    public List<Date> getAbsenceDates() {
        return absenceDates;
    }

    public void setAbsenceDates(List<Date> absenceDates) {
        this.absenceDates = absenceDates;
    }
}

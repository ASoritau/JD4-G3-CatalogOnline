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
        this.absencesPairs = new ArrayList<>();
        List<Date> absenceDates = new ArrayList<>();

        for (Grade grade : grades) {
            this.grades.add(new Pair<>(grade.getGrade(), grade.getDate()));
        }

        for (Absence absence : absences) {
            absenceDates.add(absence.getDate());
        }

        for (Date absenceDate : absenceDates) {
            List<Date> datesAdded = new ArrayList<>();

            for (Pair<Integer, Date> absencePair : absencesPairs) {
                datesAdded.add((Date) absencePair.getValue());
            }

            if (!datesAdded.contains(absenceDate)) {
                absencesPairs.add(new Pair<>(1, absenceDate));
            } else {
                Pair<Integer,Date> existingDate = absencesPairs.stream().filter(ad -> ad.getValue().equals(absenceDate)).findFirst().get();
                existingDate.setKey((Integer) existingDate.getKey() + 1);
            }
        }
    };

    private String studentName;

    private List<Pair<Integer, Date>> grades;

    private List<Pair<Integer, Date>> absencesPairs;

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

    public List<Pair<Integer, Date>> getAbsencesPairs() {
        return absencesPairs;
    }

    public void setAbsencesPairs(List<Pair<Integer, Date>> absencesPairs) {
        this.absencesPairs = absencesPairs;
    }
}

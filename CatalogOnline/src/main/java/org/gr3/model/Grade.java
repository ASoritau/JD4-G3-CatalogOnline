package org.gr3.model;

import java.sql.Date;

public class Grade {
    private String subject;
    private int grade;
    private Date date;
    private String studentName;

    public Grade(String subject, int grade, Date date, String studentName) {
        this.subject = subject;
        this.grade = grade;
        this.date = date;
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public Date getDate() {
        return date;
    }

    public String getStudentName() {
        return studentName;
    }
}

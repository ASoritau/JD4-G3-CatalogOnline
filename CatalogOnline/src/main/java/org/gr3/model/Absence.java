package org.gr3.model;

import java.sql.Date;

public class Absence {
    private Date date;
    private String subject;
    private String studentName;

    public Absence(Date date, String subject, String studentName) {
        this.date = date;
        this.subject = subject;
        this.studentName = studentName;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getStudentName() {
        return studentName;
    }
}

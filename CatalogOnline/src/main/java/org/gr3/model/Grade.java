package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

//@Entity
public class Grade {
    private String subject;
    private int grade;
    private Date date;
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Grade(String subject, int grade, Date date, String username) {
        this.subject = subject;
        this.grade = grade;
        this.date = date;
        this.username = username;
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
        return username;
    }
}

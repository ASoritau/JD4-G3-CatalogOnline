package org.gr3.CatalogOnline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Grade {
    private String subject;
    private int grade;
    private Date date;
    private String studentName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

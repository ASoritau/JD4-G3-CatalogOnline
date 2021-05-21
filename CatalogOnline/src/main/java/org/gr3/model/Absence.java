package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

//@Entity
public class Absence {
    private Date date;
    private String subject;
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Absence(Date date, String subject, String username) {
        this.date = date;
        this.subject = subject;
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getStudentName() {
        return username;
    }
}

package org.gr3.CatalogOnline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Absence {
    private Date date;
    private String subject;
    private String studentName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

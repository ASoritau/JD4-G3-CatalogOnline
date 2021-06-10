package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Grade {
    private Long subject_id;
    private int grade;
    private Date date;
    private Long student_id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Grade() {

    }

    public Grade(Long subject_id, int grade, Date date, Long student_id) {
        this.subject_id = subject_id;
        this.grade = grade;
        this.date = date;
        this.student_id = student_id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject(Long subject) {
        this.subject_id = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

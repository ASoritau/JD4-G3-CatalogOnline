package org.gr3.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@DiscriminatorValue("Teacher")
public class Teacher extends User {
    private String numarContract;
    private String subject;
    private Long subjectId;

    public Teacher() {

    }

    public Teacher(String dtype) {
        super(dtype);
    }

    public Teacher(String password, String firstName, String lastName, String email, String phoneNumber, String address) {
        super(password, firstName, lastName, email, phoneNumber, address);
    }

    public void setNumarContract(String numarContract) {
        this.numarContract = numarContract;
    }

    public String getNumarContract() {
        return numarContract;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}

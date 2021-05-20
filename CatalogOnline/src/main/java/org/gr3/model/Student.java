package org.gr3.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user")
@DiscriminatorValue("Student")
public class Student extends User {
    private String numarMatricol;

    public Student() {

    }

    public Student(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address) {
        super(username, password, firstName, lastName, email, phoneNumber, address);
    }

    public String getNumarMatricol(){
        return numarMatricol;
    }

    public void setNumarMatricol(String numarMatricol) {
        this.numarMatricol = numarMatricol;
    }
}

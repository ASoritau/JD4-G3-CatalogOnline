package org.gr3.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user")
@DiscriminatorValue("Professor")
public class Teacher extends User {
    private String numarContract;

    public Teacher() {

    }

    public Teacher(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address) {
        super(username, password, firstName, lastName, email, phoneNumber, address);
    }

    public void setNumarContract(String numarContract) {
        this.numarContract = numarContract;
    }

    public String getNumarContract() {
        return numarContract;
    }
}

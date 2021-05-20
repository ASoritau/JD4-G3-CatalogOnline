package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Subject {
    private String name;
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Subject(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public String getStudentName() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentName(String username) {
        this.username = username;
    }
}

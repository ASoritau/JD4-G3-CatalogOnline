package org.gr3.CatalogOnline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
    private String name;
    private String studentName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Subject(String name, String studentName) {
        this.name = name;
        this.studentName = studentName;
    }

    public String getName() {
        return name;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

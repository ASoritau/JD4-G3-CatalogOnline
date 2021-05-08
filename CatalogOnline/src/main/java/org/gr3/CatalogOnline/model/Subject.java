package org.gr3.CatalogOnline.model;

public class Subject {
    private String name;
    private String studentName;

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

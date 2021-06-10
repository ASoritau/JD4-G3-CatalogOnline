package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
    private String name;

    private Long teacherId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SubjectId;

    public Subject(){}

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.SubjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}

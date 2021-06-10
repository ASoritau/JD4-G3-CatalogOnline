package org.gr3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int userId;
    private Integer subjectId;

    public Connection(int userId, int subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public Connection() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}

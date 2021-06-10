package org.gr3.service;

import org.gr3.model.Connection;
import org.gr3.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;

    public Connection getTeacherSpecialization(int userId) {
        return teacherRepo.findByUserId(userId);
    }
}

package org.gr3.service;

import org.gr3.model.Grade;
import org.gr3.model.User;
import org.gr3.repo.GradeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private GradeRepo gradeRepo;

    public List<Integer> getPossibleGrades() {
        List<Integer> grades = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            grades.add(i);
        }

        return grades;
    }

    public void crateGrade(Grade grade) {
        gradeRepo.save(grade);
        LOGGER.debug("Grade added successfully.");
    }

    public List<Grade> getAllGrades() {
        return gradeRepo.findAll();
    }

    public List<Grade> getStudentGrades(int studentId) {
        return gradeRepo.findByStudentId(studentId);
    }

    public List<Grade> getGradesBySubject(String subjectName) {
        return gradeRepo.findBySubject(subjectName);
    }

    public List<Grade> getStudentGradesForSubject(int studentId, String subject) {
        return gradeRepo.findByStudentIdAndSubject(studentId, subject);
    }
}

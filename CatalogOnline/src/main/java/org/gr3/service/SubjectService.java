package org.gr3.service;

import org.gr3.model.Subject;
import org.gr3.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepo subjectRepo;

    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }

}

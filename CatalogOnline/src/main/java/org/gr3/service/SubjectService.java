package org.gr3.service;

import org.gr3.model.Subject;
import org.gr3.model.User;
import org.gr3.repo.SubjectRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepo subjectRepo;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }

    public Subject findById(int id) {
        return subjectRepo.findById(id);
    }

    public Subject findByName(String name) {
        return subjectRepo.findByName(name);
    }

    public void createSubject(Subject subject) {
        subjectRepo.save(subject);
        LOGGER.info("Subject with name \"" + subject.getName() + "\" was created.");
    }

}

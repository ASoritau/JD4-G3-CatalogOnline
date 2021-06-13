package org.gr3.service;

import org.gr3.model.Student;
import org.gr3.model.Subject;
import org.gr3.model.Teacher;
import org.gr3.model.User;
import org.gr3.repo.SubjectRepo;
import org.gr3.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Optional<User> findById(long userId) {
        return userRepo.findById(userId);
    }

    public User findByFirstName(String firstName) {
        return userRepo.findByFirstName(firstName);
    }

    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public User login(User user) {
        User loginUser = userRepo.findByEmail(user.getEmail());

        if (loginUser == null) {
            LOGGER.error("User was not found!");
            return null;
        }

        if (user.getPassword().equals(loginUser.getPassword())) {
            LOGGER.info("User has been logged in.");
            return loginUser;
        }

        throw new IllegalArgumentException("Parameter is not of type \"User\"!");
    }

    public void register(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());

        if (existingUser == null || !existingUser.getEmail().equals(user.getEmail())) {
            Teacher t;
            Student s;

            if (user.getDtype().equals("Student")) {
                s = new Student(user.getPassword(), user.getFirstName(),
                        user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
                s.setDtype("Student");
                userRepo.save(s);
                LOGGER.info("User \"" + s.getFirstName() + " " + s.getLastName() + "\" has been registered as Student.");
            }

            if (user.getDtype().equals("Teacher")) {
                t = new Teacher(user.getPassword(), user.getFirstName(),
                        user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
                t.setDtype("Teacher");
                userRepo.save(t);
                LOGGER.info("User \"" + t.getFirstName() + " " + t.getLastName() + "\" has been registered as Teacher.");
            }

        } else {
            LOGGER.info("User was found in the database.");
        }
    }

    public void registerTeacher(Teacher teacher) {
        User existingUser = userRepo.findByEmail(teacher.getEmail());

        if (existingUser == null || !existingUser.getEmail().equals(teacher.getEmail())) {
            Subject subject = subjectRepo.findByName(teacher.getSubject());

            teacher.setSubjectId(subject.getId());
            userRepo.save(teacher);
            LOGGER.info("User \"" + teacher.getFirstName() + " " + teacher.getLastName() + "\" has been registered as Teacher.");
        } else {
            LOGGER.info("User was found in the database.");
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

//    TODO: metoda care sa verifice daca exista userul
}

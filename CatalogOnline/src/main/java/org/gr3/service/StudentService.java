package org.gr3.service;

import org.gr3.model.Student;
import org.gr3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    UserService userService;

    public List<Student> getAllStuents() {
        List<User> users = userService.getAllUsers();

        List<Student> studentsList = new ArrayList<>();

        for (User user : users) {
            if (user instanceof Student) {
                studentsList.add((Student) user);
            }
        }

        return studentsList;
    }
}

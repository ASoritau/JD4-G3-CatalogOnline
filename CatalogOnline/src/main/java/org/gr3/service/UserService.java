package org.gr3.service;

import org.gr3.model.Student;
import org.gr3.model.User;
import org.gr3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

//    public List<User> getAllUsers() {
//        return userRepo.findAllUsers();
//    }

    public User findByName(String username) {
        return userRepo.findByUserName(username);
    }

    public User login(User user) {
        User loginUser = userRepo.findByEmail(user.getEmail());

        if (loginUser == null) {
            System.out.println("User not found!");
            return null;
        }

        if (user.getPassword().equals(loginUser.getPassword())) {
            System.out.println("User has been logged in.");
            return loginUser;
        }

        System.out.println("Should not get here!");
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

//    TODO: metoda care sa verifice daca exista userul
}

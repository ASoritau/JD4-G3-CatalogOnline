package org.gr3.service;

import org.gr3.model.User;
import org.gr3.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
//    public List<User> getAllUsers() {
//        return userRepo.findAllUsers();
//    }

    public User findByName(String username) {
        return userRepo.findByUsername(username);
    }

    public User login(User user) {
        User loginUser = userRepo.findByEmail(user.getEmail());

        if (loginUser == null) {
//            System.out.println("User not found!");
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
            userRepo.save(user);
            LOGGER.info("User has been registered.");
        }
        else {
            LOGGER.info("User was found in the database.");
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

//    TODO: metoda care sa verifice daca exista userul
}

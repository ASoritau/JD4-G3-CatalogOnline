package org.gr3.service;

import org.gr3.CatalogOnline.model.User;
import org.gr3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAllUsers();
    }

//    TODO: metoda care sa verifice daca exista userul
}

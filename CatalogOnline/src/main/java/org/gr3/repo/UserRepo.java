package org.gr3.repo;

import org.gr3.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByFirstName(String firstName);

    List<User> findAll();

    User findByEmail(String email);

    User findByFirstNameAndLastName(String firstName, String lastName);
}

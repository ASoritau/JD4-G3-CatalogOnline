package org.gr3.repo;

import org.gr3.model.Student;
import org.gr3.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface StudentRepo extends CrudRepository<Student, Integer> {
//    public List<Student> findByName(String name);
//    public Student findByStudentName(String username);

//    @Query("SELECT * FROM user")
//    public List<User> findAllUsers();

public interface StudentRepo {
}
//}

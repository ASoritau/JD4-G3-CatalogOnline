package org.gr3.repo;

import org.gr3.CatalogOnline.model.Student;
import org.gr3.CatalogOnline.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<Student, Long> {
    @Query("SELECT u FROM user u WHERE u.UserName=?1")
    public List<Student> searchByName(String name);

    @Query("SELECT u FROM user u WHERE u.UserName=?1 AND u.dtype = 'Student'")
    public Student searchStudentByName(String studentName);

    @Query("SELECT u FROM user u")
    public List<User> findAllUsers();
}

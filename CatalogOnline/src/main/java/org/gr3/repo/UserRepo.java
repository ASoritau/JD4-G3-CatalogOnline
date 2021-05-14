package org.gr3.repo;

import org.gr3.CatalogOnline.model.Student;
import org.gr3.CatalogOnline.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<Student, Long> {
    public List<Student> findByName(String name);
    public Student findByStudentName(String studentName);

    @Query("SELECT * FROM user")
    public List<User> findAllUsers();
}

package org.gr3.repo;

import org.gr3.model.Connection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<Connection, Long> {

//    @Query("SELECT c FROM connection c WHERE c.userId=?1")
    public Connection findByUserId(int userId);
}

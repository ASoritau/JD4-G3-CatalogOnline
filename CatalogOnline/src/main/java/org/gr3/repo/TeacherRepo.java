package org.gr3.repo;

import org.gr3.model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<Connection, Long> {

    public Connection findByUserId(int userId);
}

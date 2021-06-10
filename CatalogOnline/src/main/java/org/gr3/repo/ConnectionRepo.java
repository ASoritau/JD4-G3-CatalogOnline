package org.gr3.repo;

import org.gr3.model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepo extends CrudRepository<Connection, Long> {

    List<Connection> findAll();

    Connection findByUserId(int userId);

    Connection findBySubjectId(int subjectId);
}

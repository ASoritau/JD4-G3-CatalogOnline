package org.gr3.repo;

import org.gr3.model.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends CrudRepository<Grade, Long> {

    List<Grade> findAll();
}

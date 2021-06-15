package org.gr3.repo;

import org.gr3.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends CrudRepository<Subject, Long> {

    List<Subject> findAll();

    Subject findByName(String name);
}

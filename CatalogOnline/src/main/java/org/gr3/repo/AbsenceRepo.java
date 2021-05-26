package org.gr3.repo;

import org.gr3.model.Absence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepo extends CrudRepository<Absence, Long> {

    List<Absence> findByUserId(Integer studentId);

}

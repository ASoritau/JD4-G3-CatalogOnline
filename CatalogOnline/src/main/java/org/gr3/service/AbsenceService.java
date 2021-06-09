package org.gr3.service;

import org.gr3.model.Absence;
import org.gr3.repo.AbsenceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AbsenceRepo absenceRepo;

    public void createAbsence(Absence absence) {
        absenceRepo.save(absence);
        LOGGER.debug("Absence added successfully.");
    }

    public List<Absence> getAllAbsences() {
        return absenceRepo.findAll();
    }

    public List<Absence> getStudentAbsences(int studentId){
        return absenceRepo.findByStudentId(studentId);
    }
}

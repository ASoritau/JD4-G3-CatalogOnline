package org.gr3.service;

import org.gr3.model.Absence;
import org.gr3.repo.AbsenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepo absenceRepo;

    public void createAbsence(Absence absence) {
        absenceRepo.save(absence);
    }

    public List<Absence> getAbsencesForStudent(Integer studentId){
        return absenceRepo.findByStudentId(studentId);
    }
}

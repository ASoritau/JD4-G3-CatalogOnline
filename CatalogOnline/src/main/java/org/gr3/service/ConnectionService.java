package org.gr3.service;

import org.gr3.model.Connection;
import org.gr3.repo.ConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepo connectionRepo;

    public List<Connection> getAllConnections() {
        return connectionRepo.findAll();
    }

    public Connection findByUserId(int userId) {
        return connectionRepo.findByUserId(userId);
    }

    public Connection findBySubjectId(int subjectId) {
        return connectionRepo.findBySubjectId(subjectId);
    }
}

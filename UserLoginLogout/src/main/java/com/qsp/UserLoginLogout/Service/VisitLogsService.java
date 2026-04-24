package com.qsp.UserLoginLogout.Service;

import com.qsp.UserLoginLogout.Entity.User;
import com.qsp.UserLoginLogout.Entity.Visitlogs;
import com.qsp.UserLoginLogout.Repository.UserRepository;
import com.qsp.UserLoginLogout.Repository.VisitlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class VisitLogsService {

    @Autowired
    private VisitlogsRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Visitlogs Checkin(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Visitlogs visitlogs = new Visitlogs();
        visitlogs.setUser(user);
        visitlogs.setVisitDate(LocalDate.now());
        visitlogs.setCheckIN(LocalTime.now());
        visitlogs.setCheckOut(null);
        visitlogs.setStatus("checked-in");
        return repository.save(visitlogs);
    }

    public Visitlogs Checkout(Integer UserId) {
        Visitlogs log = repository.findById(UserId)
                .orElseThrow(() -> new RuntimeException("VisitLogs Not Found"));

        log.setCheckOut(LocalTime.now());
        log.setStatus("checked-out");
        return repository.save(log);
    }

    public List<Visitlogs> getallVisitByUser(Integer userId) {
        return repository.findByUserId(userId);
    }

    public List<Visitlogs> getallvisitos() {
        return repository.findAll();
    }
}
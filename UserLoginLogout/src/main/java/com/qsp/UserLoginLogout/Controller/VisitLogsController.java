package com.qsp.UserLoginLogout.Controller;

import com.qsp.UserLoginLogout.Entity.Visitlogs;
import com.qsp.UserLoginLogout.Service.VisitLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitLogsController {

    @Autowired
    private VisitLogsService visitLogsService;

    @PostMapping("/checkin/{userId}")
    public ResponseEntity<Visitlogs> checkIn(@PathVariable Integer userId) {
        return ResponseEntity.ok(visitLogsService.Checkin(userId));
    }

    @PutMapping("/checkout/{UserId}")
    public ResponseEntity<Visitlogs> checkout(@PathVariable Integer UserId) {
        return ResponseEntity.ok(visitLogsService.Checkout(UserId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Visitlogs>> getVisitByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(visitLogsService.getallVisitByUser(userId));
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<Visitlogs>> getallVisitors() {
        return ResponseEntity.ok(visitLogsService.getallvisitos());
    }
}
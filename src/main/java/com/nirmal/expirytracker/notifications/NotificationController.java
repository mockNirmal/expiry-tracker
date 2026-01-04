package com.nirmal.expirytracker.notifications;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationLogRepository repo;

    public NotificationController(NotificationLogRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<NotificationLog> all() {
        return repo.findAll();
    }
}

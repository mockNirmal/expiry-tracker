package com.nirmal.expirytracker.notifications;

import com.nirmal.expirytracker.items.Batch;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationLogRepository logRepo;

    public NotificationService(NotificationLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    public void recordIfNotSent(Batch batch, NotificationType type, LocalDate scheduledFor, String message) {
        boolean alreadySent = logRepo.existsByBatchIdAndTypeAndScheduledFor(batch.getId(), type, scheduledFor);
        if (alreadySent) return;

        NotificationLog log = new NotificationLog();
        log.setBatchId(batch.getId());
        log.setType(type);
        log.setScheduledFor(scheduledFor);
        log.setSentAt(LocalDateTime.now());
        log.setMessage(message);

        logRepo.save(log);

        // "Send" notification for now (console). Later: email/SMS.
        System.out.println("NOTIFY [" + type + "] " + message);
    }
}

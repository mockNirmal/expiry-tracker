package com.nirmal.expirytracker.notifications;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
    boolean existsByBatchIdAndTypeAndScheduledFor(Long batchId, NotificationType type, LocalDate scheduledFor);
}
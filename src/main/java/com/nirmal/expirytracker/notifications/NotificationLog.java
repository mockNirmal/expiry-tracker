package com.nirmal.expirytracker.notifications;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification_log",
        uniqueConstraints = @UniqueConstraint(columnNames = {"batchId", "type", "scheduledFor"}))
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long batchId;               // which batch we notified about

    @Enumerated(EnumType.STRING)
    private NotificationType type;      // EXPIRING_SOON or EXPIRES_TODAY

    private LocalDate scheduledFor;     // date the reminder is for

    private LocalDateTime sentAt;       // when we recorded/sent it

    private String message;

    public Long getId() { return id; }

    public Long getBatchId() { return batchId; }
    public void setBatchId(Long batchId) { this.batchId = batchId; }

    public NotificationType getType() { return type; }
    public void setType(NotificationType type) { this.type = type; }

    public LocalDate getScheduledFor() { return scheduledFor; }
    public void setScheduledFor(LocalDate scheduledFor) { this.scheduledFor = scheduledFor; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

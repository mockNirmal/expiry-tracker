package com.nirmal.expirytracker.notifications;

import com.nirmal.expirytracker.items.Batch;
import com.nirmal.expirytracker.items.BatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpiryScheduler {

    private final BatchService batchService;
    private final NotificationService notificationService;
    @Value("${expiry.reminder.days:7}")
    private int reminderDays;


    public ExpiryScheduler(BatchService batchService, NotificationService notificationService) {
        this.batchService = batchService;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 8 * * *") // every day at 8AM
    public void runDailyExpiryCheck() {
        LocalDate today = LocalDate.now();

        // 7-day reminder
        List<Batch> expiringSoon = batchService.expiringWithinDays(7);
        for (Batch b : expiringSoon) {
            // Only trigger exactly 7 days before (not 1..7)
            if (b.getExpiryDate().equals(today.plusDays(reminderDays))) {
                String msg = b.getName() + " (" + b.getSku() + ") expires on " + b.getExpiryDate()
                        + " qty=" + b.getQuantity();
                notificationService.recordIfNotSent(b, NotificationType.EXPIRING_SOON, b.getExpiryDate(), msg);
            }
        }

        // today reminder
        List<Batch> expiringToday = batchService.expiringToday();
        for (Batch b : expiringToday) {
            String msg = b.getName() + " (" + b.getSku() + ") EXPIRES TODAY qty=" + b.getQuantity();
            notificationService.recordIfNotSent(b, NotificationType.EXPIRES_TODAY, today, msg);
        }
    }
}

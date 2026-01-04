package com.nirmal.expirytracker.items;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatchService {

    private final BatchRepository repo;

    public BatchService(BatchRepository repo) {
        this.repo = repo;
    }

    public Batch create(Batch batch) {
        return repo.save(batch);
    }

    public List<Batch> listAll() {
        return repo.findAll();
    }

    public List<Batch> expiringWithinDays(int days) {
        LocalDate today = LocalDate.now();
        return repo.findByExpiryDateBetween(today, today.plusDays(days));
    }

    public List<Batch> expiringToday() {
        return repo.findByExpiryDate(LocalDate.now());
    }
}

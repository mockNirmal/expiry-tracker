package com.nirmal.expirytracker.items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByExpiryDateBetween(LocalDate start, LocalDate end);
    List<Batch> findByExpiryDate(LocalDate date);
}

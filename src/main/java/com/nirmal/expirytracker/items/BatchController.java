package com.nirmal.expirytracker.items;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService service;

    public BatchController(BatchService service) {
        this.service = service;
    }

    @PostMapping
    public Batch create(@Valid @RequestBody Batch batch) {
        return service.create(batch);
    }

    @GetMapping
    public List<Batch> listAll() {
        return service.listAll();
    }

    @GetMapping("/expiring")
    public List<Batch> expiring(@RequestParam(defaultValue = "7") int days) {
        return service.expiringWithinDays(days);
    }

    @GetMapping("/expiring-today")
    public List<Batch> expiringToday() {
        return service.expiringToday();
    }
}

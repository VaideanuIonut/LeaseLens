package com.leaselens.backend.controller;

import com.leaselens.backend.dto.LeaseExpiryDto;
import com.leaselens.backend.service.LeaseExpiryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lease-expiries")
public class LeaseExpiryController {
    private final LeaseExpiryService service;
    public LeaseExpiryController(LeaseExpiryService service) { this.service = service; }
    @GetMapping public List<LeaseExpiryDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public LeaseExpiryDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public LeaseExpiryDto create(@RequestBody LeaseExpiryDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public LeaseExpiryDto update(@PathVariable Integer id, @RequestBody LeaseExpiryDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

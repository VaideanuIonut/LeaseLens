package com.leaselens.backend.controller;

import com.leaselens.backend.dto.LeaseDto;
import com.leaselens.backend.service.LeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {
    private final LeaseService service;
    public LeaseController(LeaseService service) { this.service = service; }
    @GetMapping public List<LeaseDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public LeaseDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public LeaseDto create(@RequestBody LeaseDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public LeaseDto update(@PathVariable Integer id, @RequestBody LeaseDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

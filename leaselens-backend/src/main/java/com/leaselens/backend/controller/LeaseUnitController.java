package com.leaselens.backend.controller;

import com.leaselens.backend.dto.LeaseUnitDto;
import com.leaselens.backend.service.LeaseUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lease-units")
public class LeaseUnitController {
    private final LeaseUnitService service;
    public LeaseUnitController(LeaseUnitService service) { this.service = service; }
    @GetMapping public List<LeaseUnitDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public LeaseUnitDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public LeaseUnitDto create(@RequestBody LeaseUnitDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public LeaseUnitDto update(@PathVariable Integer id, @RequestBody LeaseUnitDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

package com.leaselens.backend.controller;

import com.leaselens.backend.dto.RentRollDto;
import com.leaselens.backend.service.RentRollService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rent-rolls")
public class RentRollController {
    private final RentRollService service;
    public RentRollController(RentRollService service) { this.service = service; }
    @GetMapping public List<RentRollDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public RentRollDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public RentRollDto create(@RequestBody RentRollDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public RentRollDto update(@PathVariable Integer id, @RequestBody RentRollDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

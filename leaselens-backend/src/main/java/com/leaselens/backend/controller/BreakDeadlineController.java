package com.leaselens.backend.controller;

import com.leaselens.backend.dto.BreakDeadlineDto;
import com.leaselens.backend.service.BreakDeadlineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/break-deadlines")
public class BreakDeadlineController {
    private final BreakDeadlineService service;
    public BreakDeadlineController(BreakDeadlineService service) { this.service = service; }
    @GetMapping public List<BreakDeadlineDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public BreakDeadlineDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public BreakDeadlineDto create(@RequestBody BreakDeadlineDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public BreakDeadlineDto update(@PathVariable Integer id, @RequestBody BreakDeadlineDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

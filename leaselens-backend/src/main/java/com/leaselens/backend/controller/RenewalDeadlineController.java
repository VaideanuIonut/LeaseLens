package com.leaselens.backend.controller;

import com.leaselens.backend.dto.RenewalDeadlineDto;
import com.leaselens.backend.service.RenewalDeadlineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/renewal-deadlines")
public class RenewalDeadlineController {
    private final RenewalDeadlineService service;
    public RenewalDeadlineController(RenewalDeadlineService service) { this.service = service; }
    @GetMapping public List<RenewalDeadlineDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public RenewalDeadlineDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public RenewalDeadlineDto create(@RequestBody RenewalDeadlineDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public RenewalDeadlineDto update(@PathVariable Integer id, @RequestBody RenewalDeadlineDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

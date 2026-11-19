package com.leaselens.backend.controller;

import com.leaselens.backend.dto.UnitDto;
import com.leaselens.backend.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {
    private final UnitService service;
    public UnitController(UnitService service) { this.service = service; }
    @GetMapping public List<UnitDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public UnitDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public UnitDto create(@RequestBody UnitDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public UnitDto update(@PathVariable Integer id, @RequestBody UnitDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

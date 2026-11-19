package com.leaselens.backend.controller;

import com.leaselens.backend.dto.PropertyDto;
import com.leaselens.backend.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService service;
    public PropertyController(PropertyService service) { this.service = service; }
    @GetMapping public List<PropertyDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public PropertyDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public PropertyDto create(@RequestBody PropertyDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public PropertyDto update(@PathVariable Integer id, @RequestBody PropertyDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

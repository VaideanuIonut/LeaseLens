package com.leaselens.backend.controller;

import com.leaselens.backend.dto.PropertyTypeDto;
import com.leaselens.backend.service.PropertyTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/property-types")
public class PropertyTypeController {
    private final PropertyTypeService service;
    public PropertyTypeController(PropertyTypeService service) { this.service = service; }
    @GetMapping public List<PropertyTypeDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public PropertyTypeDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public PropertyTypeDto create(@RequestBody PropertyTypeDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public PropertyTypeDto update(@PathVariable Integer id, @RequestBody PropertyTypeDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

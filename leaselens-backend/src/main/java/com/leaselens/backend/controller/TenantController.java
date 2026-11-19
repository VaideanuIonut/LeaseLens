package com.leaselens.backend.controller;

import com.leaselens.backend.dto.TenantDto;
import com.leaselens.backend.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService service;
    public TenantController(TenantService service) { this.service = service; }
    @GetMapping public List<TenantDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public TenantDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public TenantDto create(@RequestBody TenantDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public TenantDto update(@PathVariable Integer id, @RequestBody TenantDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

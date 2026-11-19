package com.leaselens.backend.controller;

import com.leaselens.backend.dto.RentArrearsDto;
import com.leaselens.backend.service.RentArrearsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rent-arrears")
public class RentArrearsController {
    private final RentArrearsService service;
    public RentArrearsController(RentArrearsService service) { this.service = service; }
    @GetMapping public List<RentArrearsDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public RentArrearsDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public RentArrearsDto create(@RequestBody RentArrearsDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public RentArrearsDto update(@PathVariable Integer id, @RequestBody RentArrearsDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

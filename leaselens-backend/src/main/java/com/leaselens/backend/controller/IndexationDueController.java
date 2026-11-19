package com.leaselens.backend.controller;

import com.leaselens.backend.dto.IndexationDueDto;
import com.leaselens.backend.service.IndexationDueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/indexation-dues")
public class IndexationDueController {
    private final IndexationDueService service;
    public IndexationDueController(IndexationDueService service) { this.service = service; }
    @GetMapping public List<IndexationDueDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public IndexationDueDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public IndexationDueDto create(@RequestBody IndexationDueDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public IndexationDueDto update(@PathVariable Integer id, @RequestBody IndexationDueDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

package com.leaselens.backend.controller;

import com.leaselens.backend.dto.UserDto;
import com.leaselens.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }
    @GetMapping public List<UserDto> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public UserDto findById(@PathVariable Integer id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public UserDto create(@RequestBody UserDto dto) { return service.create(dto); }
    @PutMapping("/{id}") public UserDto update(@PathVariable Integer id, @RequestBody UserDto dto) { return service.update(id, dto); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id) { service.delete(id); }
}

package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.UserDto;
import com.leaselens.backend.entity.User;
import com.leaselens.backend.mapper.UserMapper;
import com.leaselens.backend.repository.UserRepository;
import com.leaselens.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional(readOnly = true)
    @Override public List<UserDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public UserDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional(readOnly = true)
    @Override public UserDto findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
    }
    @Transactional
    @Override public UserDto create(UserDto dto) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Password is required");
    }
    @Transactional
    @Override public UserDto create(UserDto dto, String rawPassword) {
        validateUnique(dto.username(), dto.email(), null);
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        User entity = new User();
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPasswordHash(passwordEncoder.encode(rawPassword));
        entity.setCreatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(entity));
    }
    @Transactional
    @Override public UserDto update(Integer id, UserDto dto) {
        User entity = repository.findById(id).orElseThrow(() -> notFound(id));
        validateUnique(dto.username(), dto.email(), id);
        mapper.updateEntity(entity, dto);
        return mapper.toDto(repository.save(entity));
    }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
    private void validateUnique(String username, String email, Integer excludedId) {
        boolean emailExists = excludedId == null
                ? repository.existsByEmail(email)
                : repository.existsByEmailAndIdNot(email, excludedId);
        if (emailExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        boolean usernameExists = excludedId == null
                ? repository.existsByUsername(username)
                : repository.existsByUsernameAndIdNot(username, excludedId);
        if (usernameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already registered");
        }
    }
}

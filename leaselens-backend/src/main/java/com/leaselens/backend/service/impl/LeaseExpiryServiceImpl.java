package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.LeaseExpiryDto;
import com.leaselens.backend.entity.LeaseExpiry;
import com.leaselens.backend.mapper.LeaseExpiryMapper;
import com.leaselens.backend.repository.LeaseExpiryRepository;
import com.leaselens.backend.service.LeaseExpiryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class LeaseExpiryServiceImpl implements LeaseExpiryService {
    private final LeaseExpiryRepository repository;
    private final LeaseExpiryMapper mapper;

    public LeaseExpiryServiceImpl(LeaseExpiryRepository repository, LeaseExpiryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override public List<LeaseExpiryDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public LeaseExpiryDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public LeaseExpiryDto create(LeaseExpiryDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public LeaseExpiryDto update(Integer id, LeaseExpiryDto dto) {
        LeaseExpiry entity = repository.findById(id).orElseThrow(() -> notFound(id));
        mapper.updateEntity(entity, dto);
        return mapper.toDto(repository.save(entity));
    }
    @Transactional
    @Override public void delete(Integer id) {
        if (!repository.existsById(id)) throw notFound(id);
        repository.deleteById(id);
    }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

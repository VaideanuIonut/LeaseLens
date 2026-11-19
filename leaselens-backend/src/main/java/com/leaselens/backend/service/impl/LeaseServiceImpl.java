package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.LeaseDto;
import com.leaselens.backend.entity.Lease;
import com.leaselens.backend.mapper.LeaseMapper;
import com.leaselens.backend.repository.LeaseRepository;
import com.leaselens.backend.service.LeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {
    private final LeaseRepository repository;
    private final LeaseMapper mapper;

    public LeaseServiceImpl(LeaseRepository repository, LeaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override public List<LeaseDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public LeaseDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public LeaseDto create(LeaseDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public LeaseDto update(Integer id, LeaseDto dto) {
        Lease entity = repository.findById(id).orElseThrow(() -> notFound(id));
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

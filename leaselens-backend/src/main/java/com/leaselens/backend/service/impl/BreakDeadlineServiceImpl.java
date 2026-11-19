package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.BreakDeadlineDto;
import com.leaselens.backend.entity.BreakDeadline;
import com.leaselens.backend.mapper.BreakDeadlineMapper;
import com.leaselens.backend.repository.BreakDeadlineRepository;
import com.leaselens.backend.service.BreakDeadlineService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class BreakDeadlineServiceImpl implements BreakDeadlineService {
    private final BreakDeadlineRepository repository;
    private final BreakDeadlineMapper mapper;

    public BreakDeadlineServiceImpl(BreakDeadlineRepository repository, BreakDeadlineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override public List<BreakDeadlineDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public BreakDeadlineDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public BreakDeadlineDto create(BreakDeadlineDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public BreakDeadlineDto update(Integer id, BreakDeadlineDto dto) {
        BreakDeadline entity = repository.findById(id).orElseThrow(() -> notFound(id));
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

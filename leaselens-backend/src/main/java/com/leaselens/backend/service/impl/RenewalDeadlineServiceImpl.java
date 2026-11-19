package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.RenewalDeadlineDto;
import com.leaselens.backend.entity.RenewalDeadline;
import com.leaselens.backend.mapper.RenewalDeadlineMapper;
import com.leaselens.backend.repository.RenewalDeadlineRepository;
import com.leaselens.backend.service.RenewalDeadlineService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RenewalDeadlineServiceImpl implements RenewalDeadlineService {
    private final RenewalDeadlineRepository repository;
    private final RenewalDeadlineMapper mapper;
    public RenewalDeadlineServiceImpl(RenewalDeadlineRepository repository, RenewalDeadlineMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<RenewalDeadlineDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public RenewalDeadlineDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public RenewalDeadlineDto create(RenewalDeadlineDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public RenewalDeadlineDto update(Integer id, RenewalDeadlineDto dto) { RenewalDeadline entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

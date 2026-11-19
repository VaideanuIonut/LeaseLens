package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.RentRollDto;
import com.leaselens.backend.entity.RentRoll;
import com.leaselens.backend.mapper.RentRollMapper;
import com.leaselens.backend.repository.RentRollRepository;
import com.leaselens.backend.service.RentRollService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RentRollServiceImpl implements RentRollService {
    private final RentRollRepository repository;
    private final RentRollMapper mapper;
    public RentRollServiceImpl(RentRollRepository repository, RentRollMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<RentRollDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public RentRollDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public RentRollDto create(RentRollDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public RentRollDto update(Integer id, RentRollDto dto) { RentRoll entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

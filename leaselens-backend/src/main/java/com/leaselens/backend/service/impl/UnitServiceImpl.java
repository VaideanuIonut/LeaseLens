package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.UnitDto;
import com.leaselens.backend.entity.Unit;
import com.leaselens.backend.mapper.UnitMapper;
import com.leaselens.backend.repository.UnitRepository;
import com.leaselens.backend.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    private final UnitRepository repository;
    private final UnitMapper mapper;
    public UnitServiceImpl(UnitRepository repository, UnitMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<UnitDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public UnitDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public UnitDto create(UnitDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public UnitDto update(Integer id, UnitDto dto) { Unit entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

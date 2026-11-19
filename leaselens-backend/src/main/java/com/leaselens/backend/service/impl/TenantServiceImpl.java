package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.TenantDto;
import com.leaselens.backend.entity.Tenant;
import com.leaselens.backend.mapper.TenantMapper;
import com.leaselens.backend.repository.TenantRepository;
import com.leaselens.backend.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {
    private final TenantRepository repository;
    private final TenantMapper mapper;
    public TenantServiceImpl(TenantRepository repository, TenantMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<TenantDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public TenantDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public TenantDto create(TenantDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public TenantDto update(Integer id, TenantDto dto) { Tenant entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.PropertyTypeDto;
import com.leaselens.backend.entity.PropertyType;
import com.leaselens.backend.mapper.PropertyTypeMapper;
import com.leaselens.backend.repository.PropertyTypeRepository;
import com.leaselens.backend.service.PropertyTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {
    private final PropertyTypeRepository repository;
    private final PropertyTypeMapper mapper;
    public PropertyTypeServiceImpl(PropertyTypeRepository repository, PropertyTypeMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<PropertyTypeDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public PropertyTypeDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public PropertyTypeDto create(PropertyTypeDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public PropertyTypeDto update(Integer id, PropertyTypeDto dto) { PropertyType entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

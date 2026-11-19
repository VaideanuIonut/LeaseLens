package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.PropertyDto;
import com.leaselens.backend.entity.Property;
import com.leaselens.backend.mapper.PropertyMapper;
import com.leaselens.backend.repository.PropertyRepository;
import com.leaselens.backend.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository repository;
    private final PropertyMapper mapper;
    public PropertyServiceImpl(PropertyRepository repository, PropertyMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<PropertyDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public PropertyDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public PropertyDto create(PropertyDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public PropertyDto update(Integer id, PropertyDto dto) { Property entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

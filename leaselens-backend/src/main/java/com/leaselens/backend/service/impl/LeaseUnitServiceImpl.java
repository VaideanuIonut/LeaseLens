package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.LeaseUnitDto;
import com.leaselens.backend.entity.LeaseUnit;
import com.leaselens.backend.mapper.LeaseUnitMapper;
import com.leaselens.backend.repository.LeaseUnitRepository;
import com.leaselens.backend.service.LeaseUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class LeaseUnitServiceImpl implements LeaseUnitService {
    private final LeaseUnitRepository repository;
    private final LeaseUnitMapper mapper;

    public LeaseUnitServiceImpl(LeaseUnitRepository repository, LeaseUnitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override public List<LeaseUnitDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public LeaseUnitDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public LeaseUnitDto create(LeaseUnitDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public LeaseUnitDto update(Integer id, LeaseUnitDto dto) {
        LeaseUnit entity = repository.findById(id).orElseThrow(() -> notFound(id));
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

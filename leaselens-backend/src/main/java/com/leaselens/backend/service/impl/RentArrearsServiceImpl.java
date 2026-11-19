package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.RentArrearsDto;
import com.leaselens.backend.entity.RentArrears;
import com.leaselens.backend.mapper.RentArrearsMapper;
import com.leaselens.backend.repository.RentArrearsRepository;
import com.leaselens.backend.service.RentArrearsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RentArrearsServiceImpl implements RentArrearsService {
    private final RentArrearsRepository repository;
    private final RentArrearsMapper mapper;
    public RentArrearsServiceImpl(RentArrearsRepository repository, RentArrearsMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    @Override public List<RentArrearsDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public RentArrearsDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public RentArrearsDto create(RentArrearsDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public RentArrearsDto update(Integer id, RentArrearsDto dto) { RentArrears entity = repository.findById(id).orElseThrow(() -> notFound(id)); mapper.updateEntity(entity, dto); return mapper.toDto(repository.save(entity)); }
    @Transactional
    @Override public void delete(Integer id) { if (!repository.existsById(id)) throw notFound(id); repository.deleteById(id); }
    private ResponseStatusException notFound(Integer id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id); }
}

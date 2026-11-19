package com.leaselens.backend.service.impl;

import com.leaselens.backend.dto.IndexationDueDto;
import com.leaselens.backend.entity.IndexationDue;
import com.leaselens.backend.mapper.IndexationDueMapper;
import com.leaselens.backend.repository.IndexationDueRepository;
import com.leaselens.backend.service.IndexationDueService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class IndexationDueServiceImpl implements IndexationDueService {
    private final IndexationDueRepository repository;
    private final IndexationDueMapper mapper;

    public IndexationDueServiceImpl(IndexationDueRepository repository, IndexationDueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override public List<IndexationDueDto> findAll() { return repository.findAll().stream().map(mapper::toDto).toList(); }
    @Transactional(readOnly = true)
    @Override public IndexationDueDto findById(Integer id) { return mapper.toDto(repository.findById(id).orElseThrow(() -> notFound(id))); }
    @Transactional
    @Override public IndexationDueDto create(IndexationDueDto dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Transactional
    @Override public IndexationDueDto update(Integer id, IndexationDueDto dto) {
        IndexationDue entity = repository.findById(id).orElseThrow(() -> notFound(id));
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

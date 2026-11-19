package com.leaselens.backend.service;

import com.leaselens.backend.dto.IndexationDueDto;
import java.util.List;

public interface IndexationDueService {
    List<IndexationDueDto> findAll();
    IndexationDueDto findById(Integer id);
    IndexationDueDto create(IndexationDueDto dto);
    IndexationDueDto update(Integer id, IndexationDueDto dto);
    void delete(Integer id);
}

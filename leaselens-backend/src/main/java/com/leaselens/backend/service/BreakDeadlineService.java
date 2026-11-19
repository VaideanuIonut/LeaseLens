package com.leaselens.backend.service;

import com.leaselens.backend.dto.BreakDeadlineDto;
import java.util.List;

public interface BreakDeadlineService {
    List<BreakDeadlineDto> findAll();
    BreakDeadlineDto findById(Integer id);
    BreakDeadlineDto create(BreakDeadlineDto dto);
    BreakDeadlineDto update(Integer id, BreakDeadlineDto dto);
    void delete(Integer id);
}

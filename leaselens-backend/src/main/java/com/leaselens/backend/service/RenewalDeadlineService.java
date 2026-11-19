package com.leaselens.backend.service;

import com.leaselens.backend.dto.RenewalDeadlineDto;
import java.util.List;

public interface RenewalDeadlineService {
    List<RenewalDeadlineDto> findAll();
    RenewalDeadlineDto findById(Integer id);
    RenewalDeadlineDto create(RenewalDeadlineDto dto);
    RenewalDeadlineDto update(Integer id, RenewalDeadlineDto dto);
    void delete(Integer id);
}

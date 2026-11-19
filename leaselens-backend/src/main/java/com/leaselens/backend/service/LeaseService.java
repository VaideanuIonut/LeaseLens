package com.leaselens.backend.service;

import com.leaselens.backend.dto.LeaseDto;
import java.util.List;

public interface LeaseService {
    List<LeaseDto> findAll();
    LeaseDto findById(Integer id);
    LeaseDto create(LeaseDto dto);
    LeaseDto update(Integer id, LeaseDto dto);
    void delete(Integer id);
}

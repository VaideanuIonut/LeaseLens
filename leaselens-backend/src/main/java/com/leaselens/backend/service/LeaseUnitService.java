package com.leaselens.backend.service;

import com.leaselens.backend.dto.LeaseUnitDto;
import java.util.List;

public interface LeaseUnitService {
    List<LeaseUnitDto> findAll();
    LeaseUnitDto findById(Integer id);
    LeaseUnitDto create(LeaseUnitDto dto);
    LeaseUnitDto update(Integer id, LeaseUnitDto dto);
    void delete(Integer id);
}

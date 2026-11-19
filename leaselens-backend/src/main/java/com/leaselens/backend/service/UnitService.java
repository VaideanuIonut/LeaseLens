package com.leaselens.backend.service;

import com.leaselens.backend.dto.UnitDto;
import java.util.List;

public interface UnitService {
    List<UnitDto> findAll();
    UnitDto findById(Integer id);
    UnitDto create(UnitDto dto);
    UnitDto update(Integer id, UnitDto dto);
    void delete(Integer id);
}

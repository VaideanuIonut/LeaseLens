package com.leaselens.backend.service;

import com.leaselens.backend.dto.RentRollDto;
import java.util.List;

public interface RentRollService {
    List<RentRollDto> findAll();
    RentRollDto findById(Integer id);
    RentRollDto create(RentRollDto dto);
    RentRollDto update(Integer id, RentRollDto dto);
    void delete(Integer id);
}

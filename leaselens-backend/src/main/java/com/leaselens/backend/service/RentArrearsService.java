package com.leaselens.backend.service;

import com.leaselens.backend.dto.RentArrearsDto;
import java.util.List;

public interface RentArrearsService {
    List<RentArrearsDto> findAll();
    RentArrearsDto findById(Integer id);
    RentArrearsDto create(RentArrearsDto dto);
    RentArrearsDto update(Integer id, RentArrearsDto dto);
    void delete(Integer id);
}

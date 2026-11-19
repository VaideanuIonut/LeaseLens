package com.leaselens.backend.service;

import com.leaselens.backend.dto.LeaseExpiryDto;
import java.util.List;

public interface LeaseExpiryService {
    List<LeaseExpiryDto> findAll();
    LeaseExpiryDto findById(Integer id);
    LeaseExpiryDto create(LeaseExpiryDto dto);
    LeaseExpiryDto update(Integer id, LeaseExpiryDto dto);
    void delete(Integer id);
}

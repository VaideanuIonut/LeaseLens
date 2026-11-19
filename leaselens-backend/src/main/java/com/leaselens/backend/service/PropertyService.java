package com.leaselens.backend.service;

import com.leaselens.backend.dto.PropertyDto;
import java.util.List;

public interface PropertyService {
    List<PropertyDto> findAll();
    PropertyDto findById(Integer id);
    PropertyDto create(PropertyDto dto);
    PropertyDto update(Integer id, PropertyDto dto);
    void delete(Integer id);
}

package com.leaselens.backend.service;

import com.leaselens.backend.dto.PropertyTypeDto;
import java.util.List;

public interface PropertyTypeService {
    List<PropertyTypeDto> findAll();
    PropertyTypeDto findById(Integer id);
    PropertyTypeDto create(PropertyTypeDto dto);
    PropertyTypeDto update(Integer id, PropertyTypeDto dto);
    void delete(Integer id);
}

package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.PropertyTypeDto;
import com.leaselens.backend.entity.PropertyType;
import org.springframework.stereotype.Component;

@Component
public class PropertyTypeMapper implements CrudMapper<PropertyType, PropertyTypeDto> {
    public PropertyTypeDto toDto(PropertyType entity) { return new PropertyTypeDto(entity.getId(), entity.getName()); }
    public PropertyType toEntity(PropertyTypeDto dto) { return new PropertyType(null, dto.name()); }
    public void updateEntity(PropertyType entity, PropertyTypeDto dto) {
        entity.setName(dto.name());
    }
}

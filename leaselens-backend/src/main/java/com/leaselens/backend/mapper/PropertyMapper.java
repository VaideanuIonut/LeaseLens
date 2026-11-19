package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.PropertyDto;
import com.leaselens.backend.entity.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper implements CrudMapper<Property, PropertyDto> {
    public PropertyDto toDto(Property entity) { return new PropertyDto(entity.getId(), entity.getName(), entity.getAddress(), entity.getCity(), entity.getPropertyTypeId(), entity.getAssetManagerId(), entity.getTotalArea(), entity.getNumberOfUnits(), entity.getCreatedAt()); }
    public Property toEntity(PropertyDto dto) { return new Property(null, dto.name(), dto.address(), dto.city(), dto.propertyTypeId(), dto.assetManagerId(), dto.totalArea(), dto.numberOfUnits(), dto.createdAt()); }
    public void updateEntity(Property entity, PropertyDto dto) {
        entity.setName(dto.name());
        entity.setAddress(dto.address());
        entity.setCity(dto.city());
        entity.setPropertyTypeId(dto.propertyTypeId());
        entity.setAssetManagerId(dto.assetManagerId());
        entity.setTotalArea(dto.totalArea());
        entity.setNumberOfUnits(dto.numberOfUnits());
    }
}

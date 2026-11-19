package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.UnitDto;
import com.leaselens.backend.entity.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper implements CrudMapper<Unit, UnitDto> {
    public UnitDto toDto(Unit entity) { return new UnitDto(entity.getId(), entity.getPropertyId(), entity.getUnitNumber(), entity.getFloor(), entity.getArea(), entity.getStatus()); }
    public Unit toEntity(UnitDto dto) { return new Unit(null, dto.propertyId(), dto.unitNumber(), dto.floor(), dto.area(), dto.status()); }
    public void updateEntity(Unit entity, UnitDto dto) {
        entity.setPropertyId(dto.propertyId());
        entity.setUnitNumber(dto.unitNumber());
        entity.setFloor(dto.floor());
        entity.setArea(dto.area());
        entity.setStatus(dto.status());
    }
}

package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.LeaseUnitDto;
import com.leaselens.backend.entity.LeaseUnit;
import org.springframework.stereotype.Component;

@Component
public class LeaseUnitMapper implements CrudMapper<LeaseUnit, LeaseUnitDto> {
    public LeaseUnitDto toDto(LeaseUnit entity) { return new LeaseUnitDto(entity.getId(), entity.getLeaseId(), entity.getUnitId()); }
    public LeaseUnit toEntity(LeaseUnitDto dto) { return new LeaseUnit(null, dto.leaseId(), dto.unitId()); }
    public void updateEntity(LeaseUnit entity, LeaseUnitDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setUnitId(dto.unitId());
    }
}

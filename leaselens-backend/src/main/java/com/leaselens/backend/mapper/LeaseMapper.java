package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.LeaseDto;
import com.leaselens.backend.entity.Lease;
import org.springframework.stereotype.Component;

@Component
public class LeaseMapper implements CrudMapper<Lease, LeaseDto> {
    public LeaseDto toDto(Lease entity) { return new LeaseDto(entity.getId(), entity.getPropertyId(), entity.getTenantId(), entity.getStartDate(), entity.getEndDate(), entity.getInitialRentAmount(), entity.getDepositAmount(), entity.getStatus(), entity.getCreatedAt()); }
    public Lease toEntity(LeaseDto dto) { return new Lease(null, dto.propertyId(), dto.tenantId(), dto.startDate(), dto.endDate(), dto.initialRentAmount(), dto.depositAmount(), dto.status(), dto.createdAt()); }
    public void updateEntity(Lease entity, LeaseDto dto) {
        entity.setPropertyId(dto.propertyId());
        entity.setTenantId(dto.tenantId());
        entity.setStartDate(dto.startDate());
        entity.setEndDate(dto.endDate());
        entity.setInitialRentAmount(dto.initialRentAmount());
        entity.setDepositAmount(dto.depositAmount());
        entity.setStatus(dto.status());
    }
}

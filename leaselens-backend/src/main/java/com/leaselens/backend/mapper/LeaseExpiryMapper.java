package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.LeaseExpiryDto;
import com.leaselens.backend.entity.LeaseExpiry;
import org.springframework.stereotype.Component;

@Component
public class LeaseExpiryMapper implements CrudMapper<LeaseExpiry, LeaseExpiryDto> {
    public LeaseExpiryDto toDto(LeaseExpiry entity) { return new LeaseExpiryDto(entity.getId(), entity.getLeaseId(), entity.getExpiryDate(), entity.getActionStatus()); }
    public LeaseExpiry toEntity(LeaseExpiryDto dto) { return new LeaseExpiry(null, dto.leaseId(), dto.expiryDate(), dto.actionStatus()); }
    public void updateEntity(LeaseExpiry entity, LeaseExpiryDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setExpiryDate(dto.expiryDate());
        entity.setActionStatus(dto.actionStatus());
    }
}

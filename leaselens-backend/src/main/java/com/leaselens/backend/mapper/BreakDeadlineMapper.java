package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.BreakDeadlineDto;
import com.leaselens.backend.entity.BreakDeadline;
import org.springframework.stereotype.Component;

@Component
public class BreakDeadlineMapper implements CrudMapper<BreakDeadline, BreakDeadlineDto> {
    public BreakDeadlineDto toDto(BreakDeadline entity) { return new BreakDeadlineDto(entity.getId(), entity.getLeaseId(), entity.getWindowStartDate(), entity.getWindowEndDate(), entity.getEffectiveDate(), entity.getStatus()); }
    public BreakDeadline toEntity(BreakDeadlineDto dto) { return new BreakDeadline(null, dto.leaseId(), dto.windowStartDate(), dto.windowEndDate(), dto.effectiveDate(), dto.status()); }
    public void updateEntity(BreakDeadline entity, BreakDeadlineDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setWindowStartDate(dto.windowStartDate());
        entity.setWindowEndDate(dto.windowEndDate());
        entity.setEffectiveDate(dto.effectiveDate());
        entity.setStatus(dto.status());
    }
}

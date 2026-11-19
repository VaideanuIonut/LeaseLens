package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.RenewalDeadlineDto;
import com.leaselens.backend.entity.RenewalDeadline;
import org.springframework.stereotype.Component;

@Component
public class RenewalDeadlineMapper implements CrudMapper<RenewalDeadline, RenewalDeadlineDto> {
    public RenewalDeadlineDto toDto(RenewalDeadline entity) { return new RenewalDeadlineDto(entity.getId(), entity.getLeaseId(), entity.getDecisionDeadline(), entity.getRenewalEffectiveDate(), entity.getStatus()); }
    public RenewalDeadline toEntity(RenewalDeadlineDto dto) { return new RenewalDeadline(null, dto.leaseId(), dto.decisionDeadline(), dto.renewalEffectiveDate(), dto.status()); }
    public void updateEntity(RenewalDeadline entity, RenewalDeadlineDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setDecisionDeadline(dto.decisionDeadline());
        entity.setRenewalEffectiveDate(dto.renewalEffectiveDate());
        entity.setStatus(dto.status());
    }
}

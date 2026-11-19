package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.RentRollDto;
import com.leaselens.backend.entity.RentRoll;
import org.springframework.stereotype.Component;

@Component
public class RentRollMapper implements CrudMapper<RentRoll, RentRollDto> {
    public RentRollDto toDto(RentRoll entity) { return new RentRollDto(entity.getId(), entity.getLeaseId(), entity.getDueDate(), entity.getAmountDue(), entity.getAmountPaid(), entity.getPaymentDate(), entity.getStatus()); }
    public RentRoll toEntity(RentRollDto dto) { return new RentRoll(null, dto.leaseId(), dto.dueDate(), dto.amountDue(), dto.amountPaid(), dto.paymentDate(), dto.status()); }
    public void updateEntity(RentRoll entity, RentRollDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setDueDate(dto.dueDate());
        entity.setAmountDue(dto.amountDue());
        entity.setAmountPaid(dto.amountPaid());
        entity.setPaymentDate(dto.paymentDate());
        entity.setStatus(dto.status());
    }
}

package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.RentArrearsDto;
import com.leaselens.backend.entity.RentArrears;
import org.springframework.stereotype.Component;

@Component
public class RentArrearsMapper implements CrudMapper<RentArrears, RentArrearsDto> {
    public RentArrearsDto toDto(RentArrears entity) { return new RentArrearsDto(entity.getId(), entity.getLeaseId(), entity.getDefaultDate(), entity.getDaysOverdue(), entity.getTotalAmountOwed(), entity.getCureDeadline(), entity.getStatus()); }
    public RentArrears toEntity(RentArrearsDto dto) { return new RentArrears(null, dto.leaseId(), dto.defaultDate(), dto.daysOverdue(), dto.totalAmountOwed(), dto.cureDeadline(), dto.status()); }
    public void updateEntity(RentArrears entity, RentArrearsDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setDefaultDate(dto.defaultDate());
        entity.setDaysOverdue(dto.daysOverdue());
        entity.setTotalAmountOwed(dto.totalAmountOwed());
        entity.setCureDeadline(dto.cureDeadline());
        entity.setStatus(dto.status());
    }
}

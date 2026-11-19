package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.IndexationDueDto;
import com.leaselens.backend.entity.IndexationDue;
import org.springframework.stereotype.Component;

@Component
public class IndexationDueMapper implements CrudMapper<IndexationDue, IndexationDueDto> {
    public IndexationDueDto toDto(IndexationDue entity) { return new IndexationDueDto(entity.getId(), entity.getLeaseId(), entity.getNotificationDeadline(), entity.getIndexationDate(), entity.getAppliedPercentage(), entity.getNewRentAmount(), entity.getStatus()); }
    public IndexationDue toEntity(IndexationDueDto dto) { return new IndexationDue(null, dto.leaseId(), dto.notificationDeadline(), dto.indexationDate(), dto.appliedPercentage(), dto.newRentAmount(), dto.status()); }
    public void updateEntity(IndexationDue entity, IndexationDueDto dto) {
        entity.setLeaseId(dto.leaseId());
        entity.setNotificationDeadline(dto.notificationDeadline());
        entity.setIndexationDate(dto.indexationDate());
        entity.setAppliedPercentage(dto.appliedPercentage());
        entity.setNewRentAmount(dto.newRentAmount());
        entity.setStatus(dto.status());
    }
}

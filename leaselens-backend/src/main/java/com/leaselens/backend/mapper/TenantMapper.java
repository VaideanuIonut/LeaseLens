package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.TenantDto;
import com.leaselens.backend.entity.Tenant;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper implements CrudMapper<Tenant, TenantDto> {
    public TenantDto toDto(Tenant entity) { return new TenantDto(entity.getId(), entity.getCompanyName(), entity.getContactPerson(), entity.getEmail(), entity.getPhone(), entity.getTaxId(), entity.getCreatedAt()); }
    public Tenant toEntity(TenantDto dto) { return new Tenant(null, dto.companyName(), dto.contactPerson(), dto.email(), dto.phone(), dto.taxId(), dto.createdAt()); }
    public void updateEntity(Tenant entity, TenantDto dto) {
        entity.setCompanyName(dto.companyName());
        entity.setContactPerson(dto.contactPerson());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setTaxId(dto.taxId());
    }
}

package com.leaselens.backend.dto;

import java.time.LocalDateTime;

public record TenantDto(
        Integer id,
        String companyName,
        String contactPerson,
        String email,
        String phone,
        String taxId,
        LocalDateTime createdAt
) {}

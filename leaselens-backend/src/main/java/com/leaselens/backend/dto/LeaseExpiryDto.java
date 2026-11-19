package com.leaselens.backend.dto;

import java.time.LocalDate;

public record LeaseExpiryDto(
        Integer id,
        Integer leaseId,
        LocalDate expiryDate,
        String actionStatus
) {}

package com.leaselens.backend.dto;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LeaseDto(
        Integer id,
        Integer propertyId,
        Integer tenantId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal initialRentAmount,
        BigDecimal depositAmount,
        String status,
        LocalDateTime createdAt
) {}

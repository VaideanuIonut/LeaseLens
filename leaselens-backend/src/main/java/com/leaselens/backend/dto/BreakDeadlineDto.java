package com.leaselens.backend.dto;

import java.time.LocalDate;

public record BreakDeadlineDto(
        Integer id,
        Integer leaseId,
        LocalDate windowStartDate,
        LocalDate windowEndDate,
        LocalDate effectiveDate,
        String status
) {}

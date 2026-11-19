package com.leaselens.backend.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public record IndexationDueDto(
        Integer id,
        Integer leaseId,
        LocalDate notificationDeadline,
        LocalDate indexationDate,
        BigDecimal appliedPercentage,
        BigDecimal newRentAmount,
        String status
) {}

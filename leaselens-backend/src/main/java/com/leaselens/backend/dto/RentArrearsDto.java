package com.leaselens.backend.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public record RentArrearsDto(
        Integer id,
        Integer leaseId,
        LocalDate defaultDate,
        Integer daysOverdue,
        BigDecimal totalAmountOwed,
        LocalDate cureDeadline,
        String status
) {}

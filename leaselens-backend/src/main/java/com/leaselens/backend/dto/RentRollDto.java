package com.leaselens.backend.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public record RentRollDto(
        Integer id,
        Integer leaseId,
        LocalDate dueDate,
        BigDecimal amountDue,
        BigDecimal amountPaid,
        LocalDate paymentDate,
        String status
) {}

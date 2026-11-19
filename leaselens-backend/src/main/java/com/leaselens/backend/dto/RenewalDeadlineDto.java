package com.leaselens.backend.dto;

import java.time.LocalDate;

public record RenewalDeadlineDto(
        Integer id,
        Integer leaseId,
        LocalDate decisionDeadline,
        LocalDate renewalEffectiveDate,
        String status
) {}

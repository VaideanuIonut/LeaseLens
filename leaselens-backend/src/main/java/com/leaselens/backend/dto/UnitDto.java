package com.leaselens.backend.dto;

import java.math.BigDecimal;

public record UnitDto(
        Integer id,
        Integer propertyId,
        String unitNumber,
        Integer floor,
        BigDecimal area,
        String status
) {}

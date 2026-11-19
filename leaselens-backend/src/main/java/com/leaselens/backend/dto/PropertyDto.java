package com.leaselens.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyDto(
        Integer id,
        String name,
        String address,
        String city,
        Integer propertyTypeId,
        Integer assetManagerId,
        BigDecimal totalArea,
        Integer numberOfUnits,
        LocalDateTime createdAt
) {}

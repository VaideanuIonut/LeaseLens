package com.leaselens.backend.dto;

import java.time.LocalDateTime;

public record UserDto(
        Integer id,
        String username,
        String email,
        LocalDateTime createdAt
) {}

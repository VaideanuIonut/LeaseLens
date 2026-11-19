package com.leaselens.backend.dto;

import java.time.LocalDateTime;

public record UserDto(
        Integer id,
        String username,
        String email,
        String passwordHash,
        LocalDateTime createdAt
) {}

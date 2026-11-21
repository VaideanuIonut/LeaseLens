package com.leaselens.backend.mapper;

import com.leaselens.backend.dto.UserDto;
import com.leaselens.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements CrudMapper<User, UserDto> {
    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getCreatedAt());
    }
    public User toEntity(UserDto dto) {
        return new User(
                null,
                dto.username(),
                dto.email(),
                null,
                dto.createdAt());
    }
    public void updateEntity(User entity, UserDto dto) {
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
    }
}

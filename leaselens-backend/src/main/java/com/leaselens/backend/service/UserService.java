package com.leaselens.backend.service;

import com.leaselens.backend.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Integer id);
    UserDto create(UserDto dto);
    UserDto update(Integer id, UserDto dto);
    void delete(Integer id);
}

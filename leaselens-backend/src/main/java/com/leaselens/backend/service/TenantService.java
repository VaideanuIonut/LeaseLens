package com.leaselens.backend.service;

import com.leaselens.backend.dto.TenantDto;
import java.util.List;

public interface TenantService {
    List<TenantDto> findAll();
    TenantDto findById(Integer id);
    TenantDto create(TenantDto dto);
    TenantDto update(Integer id, TenantDto dto);
    void delete(Integer id);
}

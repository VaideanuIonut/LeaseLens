package com.leaselens.backend.mapper;

public interface CrudMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateEntity(E entity, D dto);
}

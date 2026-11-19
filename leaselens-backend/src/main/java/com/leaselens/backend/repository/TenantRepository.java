package com.leaselens.backend.repository;

import com.leaselens.backend.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {}

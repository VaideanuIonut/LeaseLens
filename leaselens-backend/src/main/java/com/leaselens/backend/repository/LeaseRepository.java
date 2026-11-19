package com.leaselens.backend.repository;

import com.leaselens.backend.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Integer> {}

package com.leaselens.backend.repository;

import com.leaselens.backend.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {}

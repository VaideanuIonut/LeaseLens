package com.leaselens.backend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "lease_units")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LeaseUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lease_id")
    private Integer leaseId;
    @Column(name = "unit_id")
    private Integer unitId;
}
